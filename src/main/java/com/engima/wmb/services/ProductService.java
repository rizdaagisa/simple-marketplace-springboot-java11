package com.engima.wmb.services;

import com.engima.wmb.entity.Product;
import com.engima.wmb.entity.Store;
import com.engima.wmb.entity.TransactionDetail;
import com.engima.wmb.model.request.product.ProductRequest;
import com.engima.wmb.model.response.DataResponse;
import com.engima.wmb.repository.ProductRepository;
import com.engima.wmb.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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
    public Object saveProduct(ProductRequest productRequest){ // SAVE product
        Optional<Store> storeFind = storeRepository.findById(productRequest.getStore_id());
        if(storeFind.isPresent()){

            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .stock(productRequest.getStock())
                    .build();

            Store store = storeFind.get(); // get store data
            // Set the product's store
            product.setStore(store);

            // Save the product
            productRepository.save(product);

            // Update the store's product list
            List<Product> productList = store.getProducts();
            productList.add(product);
            store.setProducts(productList);
            storeRepository.save(store);

            return DataResponse.builder().status(200).message("OK").build();

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Store tidak ditemukan with ID : " + productRequest.getStore_id());
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
