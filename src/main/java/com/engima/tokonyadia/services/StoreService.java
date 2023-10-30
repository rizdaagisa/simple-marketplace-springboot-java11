package com.engima.tokonyadia.services;

import com.engima.tokonyadia.entity.Store;
import com.engima.tokonyadia.model.request.store.StoreSignUpRequest;
import com.engima.tokonyadia.model.response.DataResponse;
import com.engima.tokonyadia.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public Object findAll(){
        return storeRepository.findAll();
    }

    public Object findById(String id){
        return storeRepository.findById(id);
    }

    @Transactional
    public Object saveStore(StoreSignUpRequest data){

        Optional<Store> storeData = storeRepository.findBysiupNumber(data.getSiup_number());

        if (storeData.isPresent()) throw new ResponseStatusException(HttpStatus.FOUND, "SIUP sudah terdaftar");

        Store store = Store.builder()
                .name(data.getName())
                .address(data.getAddress())
                .phone_number(data.getPhone_number())
                .siupNumber(data.getSiup_number())
                .build();
        storeRepository.save(store);
        return DataResponse.builder().data("Succsessfull Created!").status(200).message("OK").build();
    }

    @Transactional
    public Object deleteById(String id){
        if (storeRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID tidak ada");
        storeRepository.deleteById(id);
        return DataResponse.builder().data("Succsessfull Deleted!").status(200).message("OK").build();
    }
}
