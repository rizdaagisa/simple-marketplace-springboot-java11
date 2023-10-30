package com.engima.wmb.repository;

import com.engima.wmb.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

    @Query("SELECT u FROM UserCredential u WHERE u.email = :email")
    UserCredential findByEmail(@Param("email") String email);

//    UserCredential findByEmail(String email);

}
