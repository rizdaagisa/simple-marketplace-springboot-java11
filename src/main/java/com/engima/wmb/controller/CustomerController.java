package com.engima.wmb.controller;

import ch.qos.logback.classic.Logger;
import com.engima.wmb.entity.Customer;
import com.engima.wmb.entity.Store;
import com.engima.wmb.entity.UserCredential;
import com.engima.wmb.repository.CustomerRepository;
import com.engima.wmb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/credential") // SAVE user Credential with Customer Data
    public Object credential(@RequestBody Customer customer){
        return customerService.saveCredential(customer);
    }

    @PostMapping("/customer/email") // FIND BY EMAIL
    public Object credential(@RequestBody UserCredential userCredential){
        return customerService.findByEmail(userCredential.getEmail());
    }

    @GetMapping("/customer") // FIND ALLlogger.debug("Fetching all products");
    public Object findAll(){
        return customerService.findAll();
    }

    @PostMapping("/customer") // SAVE
    public Object saveStore(@RequestBody Customer customer){
        return customerService.saveStore(customer);
    }

    @PutMapping("/customer") // UPDATE
    public Object updateStore(@RequestBody Customer customer){
        return customerService.saveStore(customer);
    }

    @GetMapping("/customer/{id}") // FIND BY ID
    public Object findById(@PathVariable(name = "id") String id){
        return customerService.findById(id);
    }

    @DeleteMapping("/customer/{id}") // DELETE Customer BY ID
    public Object deleteById(@PathVariable(name = "id") String id){
        return customerService.deleteById(id);
    }

}
