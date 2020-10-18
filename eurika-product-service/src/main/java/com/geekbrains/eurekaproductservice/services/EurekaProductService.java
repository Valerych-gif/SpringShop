package com.geekbrains.eurekaproductservice.services;

import com.geekbrains.eurekaproductservice.repositories.EurekaProductRepository;
import com.geekbrains.springshop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurekaProductService {
    @Autowired
    private EurekaProductRepository eurekaProductRepository;

    public EurekaProductRepository getEurekaProductRepository() {
        return eurekaProductRepository;
    }

    public List<Product> getProducts(){
        return eurekaProductRepository.findAll();
    }
}
