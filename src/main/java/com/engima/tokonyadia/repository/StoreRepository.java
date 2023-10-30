package com.engima.tokonyadia.repository;
import com.engima.tokonyadia.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findBysiupNumber(String siup);
}
