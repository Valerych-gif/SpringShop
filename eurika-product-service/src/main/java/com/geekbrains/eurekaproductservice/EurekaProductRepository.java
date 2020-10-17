package com.geekbrains.eurekaproductservice;

import com.geekbrains.springshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EurekaProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
