package br.com.tirolezo.imeatapi.services;

import br.com.tirolezo.imeatapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByLogin(username);
        return user;
    }
}
