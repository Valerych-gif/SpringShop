package com.geekbrains.eurekaproductservice.controllers;

import com.geekbrains.springshop.entities.Product;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface EurekaProductController {

    @RequestMapping("/getproducts")
    List<Product> getProducts();

}
