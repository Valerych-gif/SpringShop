package com.geekbrains.eurekaproductservice;

import com.geekbrains.springshop.entities.Product;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EurekaProductControllerImpl implements EurekaProductController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private EurekaProductRepository eurekaProductRepository;

    @Override
    public List<Product> getProducts() {
        return eurekaProductRepository.findAll();
    }

}
