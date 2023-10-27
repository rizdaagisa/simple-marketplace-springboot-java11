package com.engima.wmb.controller;

import com.engima.wmb.entity.Customer;
import com.engima.wmb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/hello")
    public Object hello(@RequestParam(required = false, value = "name") String name,
                        @RequestParam(value = "name", defaultValue = "null") String city)
    {
        return name+ "<br> "+ city;
    }

    @GetMapping("/customers/{id}")
    public Object hello(@PathVariable(name = "id") Integer id){
        return "Hello "+ id;
    }

    @PostMapping("/customers")
    public Object saveCustomers(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

}
