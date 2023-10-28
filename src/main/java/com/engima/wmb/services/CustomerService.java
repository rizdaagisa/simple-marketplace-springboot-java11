package com.engima.wmb.services;

import com.engima.wmb.entity.Customer;
import com.engima.wmb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Object findAll(){
        return customerRepository.findAll();
    }

    public Object findById(String id){
        Optional<Customer> customer = customerRepository.findById(id);
        return (customer != null) ? ResponseEntity.ok(customer).getBody() : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    public Object saveStore(Customer data){
        Customer customer = customerRepository.save(data);
        return (customer != null) ? ResponseEntity.ok(customer).getBody() : null;
    }

    public Object deleteById(String id){
        if(customerRepository.findById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Customer saveCredential(Customer customer){
        return customerRepository.save(customer);
    }

    public Object findByEmail(String email){
        Customer customer = customerRepository.findByEmail(email);
        return  (customer != null) ? ResponseEntity.ok(customer).getBody() : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
    }
}
