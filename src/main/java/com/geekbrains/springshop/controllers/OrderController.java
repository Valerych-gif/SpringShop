package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.OrderItem;
import com.geekbrains.springshop.services.OrderService;
import com.geekbrains.springshop.services.ShoppingCartService;
import com.geekbrains.springshop.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/fill")
    public String fillOrder(HttpSession httpSession) {
        orderService.createOrder(httpSession);
        return "redirect:/shop";
    }
}
