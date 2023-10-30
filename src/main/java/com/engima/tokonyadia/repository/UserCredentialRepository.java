package com.engima.tokonyadia.repository;

import com.engima.tokonyadia.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

//    @Query("SELECT u FROM UserCredential u WHERE u.email = :email")
//    UserCredential findByEmail(@Param("email") String email);


    Optional<UserCredential> findByEmail(String email);

}
