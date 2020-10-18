package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("springshop-productservice-client")
public interface EurekaClient {
    @RequestMapping("/springshop/getproducts")
    List<Product> getProducts();

}