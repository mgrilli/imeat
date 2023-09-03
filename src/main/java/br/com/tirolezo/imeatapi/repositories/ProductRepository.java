package br.com.tirolezo.imeatapi.repositories;

import br.com.tirolezo.imeatapi.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
