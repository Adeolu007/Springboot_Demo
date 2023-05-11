package com.second.springbootdemo.Repository;

import com.second.springbootdemo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String productName);
    Boolean existsByName(String productName);

    Boolean existsByCreatedAt(LocalDateTime date);

    Product findByCreatedAt (LocalDateTime date);

}
