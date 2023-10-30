package com.engima.tokonyadia.repository;

import com.engima.tokonyadia.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
