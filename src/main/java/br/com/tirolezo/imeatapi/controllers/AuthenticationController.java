package br.com.tirolezo.imeatapi.controllers;

import br.com.tirolezo.imeatapi.configs.security.TokenService;
import br.com.tirolezo.imeatapi.domain.user.AuthenticationDTO;

import br.com.tirolezo.imeatapi.domain.user.LoginResponseDTO;
import br.com.tirolezo.imeatapi.domain.user.RegisterDTO;
import br.com.tirolezo.imeatapi.domain.user.User;
import br.com.tirolezo.imeatapi.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var newUser = new User(data.login(), data.email(), encryptedPassword);

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
