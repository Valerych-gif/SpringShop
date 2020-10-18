package com.geekbrains.eurekaproductservice.controllers;

import com.geekbrains.springshop.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface EurekaProductController {

    @GetMapping("/getproducts")
    List<Product> getProducts();

}
