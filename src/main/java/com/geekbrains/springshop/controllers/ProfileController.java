package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.Order;
import com.geekbrains.springshop.entities.OrderItem;
import com.geekbrains.springshop.entities.User;
import com.geekbrains.springshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String profilePage(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile/profile";
    }

    @GetMapping("/order/{id}")
    public String orderPage(Model model, @PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        List<OrderItem> orderItems = orderService.getOrderItems(id);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", order);
        return "profile/order";
    }
}
