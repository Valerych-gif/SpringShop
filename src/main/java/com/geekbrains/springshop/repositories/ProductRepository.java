package com.geekbrains.springshop.repositories;

import com.geekbrains.springshop.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
//public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByVendorCode(String vendorCode);

    List<Product> findAll();

    List<Product> findAllByTitleAndPrice(String title, double price);


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
//
//    Iterable<Product> findAll(Sort sort);
}
