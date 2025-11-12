package org.reda.productservice.service;

import org.reda.productservice.dto.ProductRequest;
import org.reda.productservice.dto.ProductResponse;
import org.reda.productservice.model.Product;
import org.reda.productservice.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductService {

    private final productRepository productRepository;

    public ProductService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> findAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mappingProduct(product)).toList();
    }


    public ProductResponse mappingProduct(Product product){
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return response;
    }
}
