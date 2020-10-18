package com.geekbrains.springshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetProductsController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/getproduct")
    void getProduct(Model model){
        System.out.println(eurekaClient.getProducts());
    }
}
