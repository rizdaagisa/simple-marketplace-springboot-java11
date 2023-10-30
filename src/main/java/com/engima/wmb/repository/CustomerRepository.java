package com.engima.wmb.repository;

import com.engima.wmb.entity.Customer;
import com.engima.wmb.entity.Store;
import com.engima.wmb.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c JOIN FETCH c.userCredential WHERE c.userCredential.email = :email")
    Customer findByEmail(@Param("email") String email);

    Customer findByUserCredentialId(String user_credential_id);

}

