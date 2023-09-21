package br.com.tirolezo.imeatapi.controllers;

import br.com.tirolezo.imeatapi.domain.product.Product;
import br.com.tirolezo.imeatapi.domain.product.ProductDTO;
import br.com.tirolezo.imeatapi.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = repository.findAll()
                .stream()
                .toList();

        if(products.isEmpty()) {
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProductById(@PathVariable("product-id") String id) {
        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveProduct(@RequestBody @Valid Product product) {
        repository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product registered successfully!");
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product-id") String id) {
        Optional<Product> product = repository.findById(id);
        if(!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product deleted successfully");
    }

}
