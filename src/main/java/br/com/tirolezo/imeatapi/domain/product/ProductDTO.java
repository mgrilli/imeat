package br.com.tirolezo.imeatapi.domain.product;

import java.math.BigDecimal;

public record ProductDTO(String prodName, Integer amount, BigDecimal price) {
    public ProductDTO(Product product) {
        this(product.getProdName(), product.getAmount(), product.getPrice());
    }
}
