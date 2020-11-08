package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.Order;
import com.geekbrains.springshop.entities.OrderItem;
import com.geekbrains.springshop.entities.Product;
import com.geekbrains.springshop.entities.User;
import com.geekbrains.springshop.repositories.OrderRepository;
import com.geekbrains.springshop.repositories.ProductRepository;
import com.geekbrains.springshop.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        order.setPhoneNumber(user.getPhone());
        order.setStatus(orderStatusService.getStatusById(1L));
        order.setPrice(cart.getTotalCost());
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void saveOrder(Order order) {
        Order orderOut = orderRepository.save(order);
        orderOut.setConfirmed(true);
    }

    public void changeOrderStatus(Order order, Long statusId) {
        order.setStatus(orderStatusService.getStatusById(statusId));
        Collection<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems){
            Long product_id = orderItem.getProduct().getId();
            Product product = productRepository.findOneById(product_id);
            long itemsQuantity = product.getQuantity()-orderItem.getQuantity();
            if (itemsQuantity>=0) {
                productRepository.decreaseProductQuantity(product_id, itemsQuantity);
            } else {
                return;
            }
        }
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long id){
        return orderRepository.findAllByUserId(id);
    }

    public List<OrderItem> getOrderItems(Long order_id){
        Order order = findById(order_id);
        return order.getOrderItems();
    }

    public List<Order> getAllUndeliveredOrders() {
        return getAllOrders().stream()
                .filter(order -> !order.getStatus().getTitle().equals("Доставлен"))
                .collect(Collectors.toList());
    }
}
