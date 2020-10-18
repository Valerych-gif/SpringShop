package com.geekbrains.eurekaproductservice.controllers;

import com.geekbrains.eurekaproductservice.services.EurekaProductService;
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
    private EurekaProductService eurekaProductService;

    public EurekaClient getEurekaClient() {
        return eurekaClient;
    }

    public EurekaProductService getEurekaProductService() {
        return eurekaProductService;
    }

    @Override
    public List<Product> getProducts() {
        return eurekaProductService.getProducts();
    }

}
