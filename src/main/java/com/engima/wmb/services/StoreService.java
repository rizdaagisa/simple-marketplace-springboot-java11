package com.engima.wmb.services;

import com.engima.wmb.entity.Store;
import com.engima.wmb.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Store saveStore(Store data){
        return storeRepository.save(data);
    }

    public Object deleteById(String id){
        if(storeRepository.findById(id) == null){
            return "404";
        }
        storeRepository.deleteById(id);
        return "200ok";
    }
}
