package com.engima.wmb.services;

import com.engima.wmb.entity.Product;
import com.engima.wmb.entity.Store;
import com.engima.wmb.entity.TransactionDetail;
import com.engima.wmb.model.response.DataResponse;
import com.engima.wmb.repository.ProductRepository;
import com.engima.wmb.repository.StoreRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Object findAll(){ // FIND ALL
        List<Product> product = productRepository.findAll();
        return DataResponse.builder().data(product).status(200).message("OK").build();
    }

    public Object findById(String id){ // FIND BY ID
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data product tidak ditemukan")
        );
        return DataResponse.builder().data(product).status(200).message("OK").build();
    }

    @Transactional
    public Object saveProduct(Product product){ // SAVE product
        Optional<Store> storeFind = storeRepository.findById(product.getStore().getId());
        if(storeFind.isPresent()){

            Store store = storeFind.get();
            // Set the product's store
            product.setStore(store);

            // Save the product
            productRepository.save(product);

            // Update the store's product list
            List<Product> productList = store.getProducts();
            productList.add(product);
            store.setProducts(productList);

            productRepository.save(product);
            return DataResponse.builder().status(200).message("OK").build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Store tidak ditemukan with ID : " + product.getStore().getId());
        }
    }

    public Object deleteById(String id){ // DELETE by ID
        Optional<Product> productFind = productRepository.findById(id);
        if (productFind.isPresent()) {

            Product product = productFind.get();

            // Remove the product from transaction details relation
            List<TransactionDetail> transactionDetails = product.getTransactionDetails();
            if (transactionDetails != null) {
                transactionDetails.forEach(transactionDetail -> transactionDetail.setProduct(null));
            }

            Store store = product.getStore();
            if(product != null){
                // Remove the product from Store relation
                store.getProducts().remove(product);
                productRepository.deleteById(id);
                return DataResponse.builder().data("Succsessful Deleted").status(200).message("OK").build();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Product tidak ditemukan with ID : " + id);
    }
}
