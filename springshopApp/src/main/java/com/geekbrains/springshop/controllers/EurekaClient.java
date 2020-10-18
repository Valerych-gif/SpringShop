package com.geekbrains.springshop.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("springshop-productservice-client")
public interface EurekaClient {
    @RequestMapping("/getproducts")
    String getProducts();

}