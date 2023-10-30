package com.engima.wmb.controller;

import com.engima.wmb.model.request.product.ProductRequest;
import com.engima.wmb.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product") // FIND ALL
    public Object findAll(){
        return productService.findAll();
    }

    @PostMapping("/product") // SAVE
    public Object saveProduct(@RequestBody ProductRequest product){
        return productService.saveProduct(product);
    }

    @PutMapping("/product") // UPDATE
    public Object updateProduct(@RequestBody ProductRequest product){
        return productService.saveProduct(product);
    }

    @GetMapping("/product/{id}") // FIND BY ID
    public Object findById(@PathVariable(name = "id") String id){
        return productService.findById(id);
    }

    @DeleteMapping("/product/{id}") // DELETE Product BY ID
    public Object deleteById(@PathVariable(name = "id") String id){
        return productService.deleteById(id);
    }
}
