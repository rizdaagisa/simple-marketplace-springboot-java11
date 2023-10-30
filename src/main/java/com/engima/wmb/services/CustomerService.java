package com.engima.wmb.services;

import com.engima.wmb.entity.Customer;
import com.engima.wmb.entity.UserCredential;
import com.engima.wmb.model.request.customer.FindByEmailRequest;
import com.engima.wmb.model.request.customer.LoginRequest;
import com.engima.wmb.model.request.customer.SignUpRequest;
import com.engima.wmb.model.response.DataResponse;
import com.engima.wmb.model.response.customer.FindByEmailResponse;
import com.engima.wmb.repository.CustomerRepository;
import com.engima.wmb.repository.UserCredentialRepository;
import lombok.Builder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public Object login(LoginRequest login){
        Customer customerEmail = customerRepository.findByEmail(login.getEmail());
        if (customerEmail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email tidak ditemukan!!");
        }

        if (customerEmail.getUserCredential().getPassword().equals(login.getPassword())){
            return DataResponse.builder().data("Login Succsessfull!").status(200).message("OK").build();
        }
        System.out.println(customerEmail.getUserCredential().getPassword() + " : " + login.getPassword());
        if(customerEmail.getUserCredential().getPassword() != login.getPassword()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password Salah!!");
        }
        return DataResponse.builder().data("Login Succsessfull!").status(200).message("OK").build();
    }

    public Object findByEmail(FindByEmailRequest email){
        UserCredential userCredential = userCredentialRepository.findByEmail(email.getEmail());
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUserCredentialId(userCredential.getId()));
        if (customer.isPresent()){
            FindByEmailResponse response = FindByEmailResponse.builder()
                    .customer(customer.get())
                    .build();
            return DataResponse.builder().data(response).status(200).message("OK").build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email tidak ditemukan!! ");
    }

    public Object saveCredential(Customer customer){
        return customerRepository.save(customer);
    }

    public Object signUp(SignUpRequest signUpRequest){

        UserCredential userCredential = UserCredential.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();

        Customer customer = Customer.builder()
                .name(signUpRequest.getName())
                .mobilePhone(signUpRequest.getMobilePhone())
                .isMember(signUpRequest.getIsMember())
                .userCredential(userCredential)
                .build();

        return customerRepository.save(customer);
    }

    public Object findAll(){
        return customerRepository.findAll();
    }

    public Object findById(String id){
        System.out.println(id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer tidak ditemukan!! ");
        }
        return DataResponse.builder().data(customer).status(200).message("OK").build();
    }

    public Object saveCustomer(Customer data){
        Customer customer = customerRepository.save(data);
        if (customer == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Save Failed");
        }
        return DataResponse.builder().status(200).message("OK").build();
    }

    public Object deleteById(String id){
        if(customerRepository.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer tidak ditemukan!!");
        }
        customerRepository.deleteById(id);
        return DataResponse.builder().status(200).message("OK").build();
    }

}
