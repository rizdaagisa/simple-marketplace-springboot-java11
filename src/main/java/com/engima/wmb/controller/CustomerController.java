package com.engima.wmb.controller;

import ch.qos.logback.classic.Logger;
import com.engima.wmb.entity.Customer;
import com.engima.wmb.entity.UserCredential;
import com.engima.wmb.model.request.customer.FindByEmailRequest;
import com.engima.wmb.model.request.customer.LoginRequest;
import com.engima.wmb.model.request.customer.SignUpRequest;
import com.engima.wmb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/login") // Login Customer
    public Object login(@RequestBody LoginRequest loginRequest){
        return customerService.login(loginRequest);
    }

    @PostMapping("/customer/credential") // SAVE user Credential with Customer Data
    public Object credential(@RequestBody Customer customer){
        return customerService.saveCredential(customer);
    }

    @PostMapping("/customer/signup") // SAVE user with signup
    public Object signUp(@RequestBody SignUpRequest signUpRequest){
        return customerService.signUp(signUpRequest);
    }

    @PostMapping("/customer/email") // FIND BY EMAIL
    public Object findByEmail(@RequestBody FindByEmailRequest email){
        return customerService.findByEmail(email);
    }

    @GetMapping("/customer") // FIND ALL
    public Object findAll(){
        return customerService.findAll();
    }

    @PostMapping("/customer") // SAVE
    public Object saveStore(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customer") // UPDATE
    public Object updateStore(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
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
