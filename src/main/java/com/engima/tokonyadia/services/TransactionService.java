package com.engima.tokonyadia.services;

import com.engima.tokonyadia.entity.Transaction;
import com.engima.tokonyadia.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public Object findAll(){
        return transactionRepository.findAll();
    }

    public Object findById(String id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return (transaction.isPresent()) ? ResponseEntity.ok(transaction).getBody() : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }

    public Object saveTransaction(Transaction data){

//        Optional<Transaction> customer = transactionRepository.findById(data.getCustomer().getId());
//
//        Optional<Product> product = transactionRepository.findById()

        Transaction transaction = transactionRepository.save(data);
        return (transaction != null) ? ResponseEntity.ok(transaction).getBody() : null;
    }


}
