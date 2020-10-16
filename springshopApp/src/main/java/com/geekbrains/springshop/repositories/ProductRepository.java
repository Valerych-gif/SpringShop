package com.geekbrains.springshop.repositories;

import com.geekbrains.springshop.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
//public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByVendorCode(String vendorCode);

    List<Product> findAll();

    List<Product> findAllByTitleAndPrice(String title, double price);

    List<Product> findAllByCategory_id(Long id);

    List<Product> findAllByPriceBetween(double min, double max);
//
//    Product findOneByTitleAndId(String title, Long id);
//
//  //  Product findOneByVendorCode
//
    Product findOneByTitle(String title);
//

    @Query(value ="select id, category_id, short_description, " +
            "create_at, full_description, title, price, vendor_code, " +
            "title from products where id = ?1", nativeQuery = true)
    Product myQuery(Long id);


    @Transactional
    @Modifying
    @Query (value = "UPDATE `products` SET `quantity` = ?2 WHERE `id` = ?1", nativeQuery = true)
    void decreaseProductQuantity(Long id, Long itemsQuantity);

    Product findOneById(Long id);
//
//    Iterable<Product> findAll(Sort sort);
}
