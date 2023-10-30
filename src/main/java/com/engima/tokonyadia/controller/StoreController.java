package com.engima.tokonyadia.controller;

import com.engima.tokonyadia.entity.Store;
import com.engima.tokonyadia.model.request.store.StoreSignUpRequest;
import com.engima.tokonyadia.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/store") // FIND ALL
    public Object findAll(){
        return storeService.findAll();
    }

    @PostMapping("/store") // SAVE
    public Object saveStore(@RequestBody StoreSignUpRequest store){
        return storeService.saveStore(store);
    }

    @PutMapping("/store") // UPDATE
    public Object updateStore(@RequestBody StoreSignUpRequest store){
        return storeService.saveStore(store);
    }

    @GetMapping("/store/{id}") // FIND BY ID
    public Object findById(@PathVariable(name = "id") String id){
        return storeService.findById(id);
    }

    @DeleteMapping("/store/{id}") // DELETE BY ID
    public Object deleteById(@PathVariable(name = "id") String id){
        return storeService.deleteById(id);
    }

}
