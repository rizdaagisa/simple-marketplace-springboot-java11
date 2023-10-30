package com.engima.tokonyadia.controller;

import com.engima.tokonyadia.entity.Customer;
import com.engima.tokonyadia.model.request.customer.FindByEmailRequest;
import com.engima.tokonyadia.model.request.customer.LoginRequest;
import com.engima.tokonyadia.model.request.customer.SignUpRequest;
import com.engima.tokonyadia.services.CustomerService;
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

    @PutMapping("/customer") // UPDATE
    public Object updateStore(@RequestBody SignUpRequest customer){
        return customerService.signUp(customer);
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
