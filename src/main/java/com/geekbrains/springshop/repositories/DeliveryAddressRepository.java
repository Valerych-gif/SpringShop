package com.geekbrains.springshop.repositories;

import com.geekbrains.springshop.entities.DeliveryAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findAllByUserId(Long userId);
}
