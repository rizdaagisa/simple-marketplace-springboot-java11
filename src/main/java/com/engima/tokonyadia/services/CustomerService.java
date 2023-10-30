package com.engima.tokonyadia.services;

import com.engima.tokonyadia.entity.Customer;
import com.engima.tokonyadia.entity.UserCredential;
import com.engima.tokonyadia.model.request.customer.FindByEmailRequest;
import com.engima.tokonyadia.model.request.customer.LoginRequest;
import com.engima.tokonyadia.model.request.customer.SignUpRequest;
import com.engima.tokonyadia.model.response.DataResponse;
import com.engima.tokonyadia.model.response.customer.FindByEmailResponse;
import com.engima.tokonyadia.model.response.customer.SignUpResponse;
import com.engima.tokonyadia.repository.CustomerRepository;
import com.engima.tokonyadia.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public Object login(LoginRequest login){
        Optional<UserCredential> customerEmail = userCredentialRepository.findByEmail(login.getEmail());
        if (customerEmail.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email tidak ditemukan!!");
        }

        if (!customerEmail.get().getPassword().equals(login.getPassword())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password Salah!!");
        }

        return DataResponse.builder().data("Login Succsessfull!").status(200).message("OK").build();
    }

    public Object findByEmail(FindByEmailRequest email){
        Optional<UserCredential> userCredential = userCredentialRepository.findByEmail(email.getEmail());
        if (userCredential.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email tidak ditemukan!! ");
        }
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUserCredentialId(userCredential.get().getId()));
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

        Optional<UserCredential> userCredential = userCredentialRepository.findByEmail(signUpRequest.getEmail());

//        System.out.println(userCredential.getEmail());
        if (userCredential.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND, "Email Sudah terdaftar");
        }


        Customer customer = Customer.builder()
                .name(signUpRequest.getName())
                .mobilePhone(signUpRequest.getMobilePhone())
                .isMember(signUpRequest.getIsMember())
                .userCredential(UserCredential.builder()
                        .password(signUpRequest.getPassword())
                        .email(signUpRequest.getEmail())
                        .build())
                .build();

        SignUpResponse response = SignUpResponse.builder()
                .name(signUpRequest.getName())
                .mobilePhone(signUpRequest.getMobilePhone())
                .isMember(signUpRequest.getIsMember())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();

        customerRepository.saveAndFlush(customer);

        return DataResponse.builder().data(customer).status(200).message("OK").build();

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

    public Object deleteById(String id){
        if(customerRepository.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer tidak ditemukan!!");
        }
        customerRepository.deleteById(id);
        return DataResponse.builder().status(200).message("OK").build();
    }

}
