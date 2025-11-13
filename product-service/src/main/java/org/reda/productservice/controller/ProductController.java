package org.reda.productservice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.reda.productservice.dto.ProductRequest;
import org.reda.productservice.dto.ProductResponse;
import org.reda.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findProducts(){
        return productService.findAllProducts();
    }
}
