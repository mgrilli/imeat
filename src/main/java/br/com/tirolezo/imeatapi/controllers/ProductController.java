package br.com.tirolezo.imeatapi.controllers;

import br.com.tirolezo.imeatapi.domain.product.Product;
import br.com.tirolezo.imeatapi.domain.product.ProductDTO;
import br.com.tirolezo.imeatapi.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = repository.findAll()
                .stream()
                .map(ProductDTO::new)
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
        var response = new ProductDTO(product.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(repository.save(product));
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
