package com.engima.tokonyadia.repository;

import com.engima.tokonyadia.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

//    @Query("SELECT c FROM Customer c JOIN FETCH c.userCredential WHERE c.userCredential.email = :email")
//    Customer findByEmail(@Param("email") String email);

    Customer findByUserCredentialId(String user_credential_id);

}

