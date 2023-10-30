package com.engima.tokonyadia.repository;

import com.engima.tokonyadia.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
