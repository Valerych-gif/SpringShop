package com.geekbrains.springshop.repositories;

import com.geekbrains.springshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
