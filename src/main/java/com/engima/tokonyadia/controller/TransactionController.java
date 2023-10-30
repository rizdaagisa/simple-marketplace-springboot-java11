package com.engima.tokonyadia.controller;

import com.engima.tokonyadia.entity.Transaction;
import com.engima.tokonyadia.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction") // FIND ALL
    public Object findAll(){
        return transactionService.findAll();
    }

    @PostMapping("/transaction") // SAVE
    public Object saveStore(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/transaction/{id}") // FIND BY ID
    public Object findById(@PathVariable(name = "id") String id){
        return transactionService.findById(id);
    }
}
