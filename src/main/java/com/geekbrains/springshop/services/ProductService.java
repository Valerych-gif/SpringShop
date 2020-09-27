package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.Product;
import com.geekbrains.springshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
//       Product products = productRepository.findOneByTitle("Коротко: Хороший телевизор Samsung 20");
//
//        productRepository.delete(products);

     //   Product product = productRepository.myQuery(1L);
    //    List<Product> products = productRepository.findAllByPriceBetween(30000.0, 40000.0);
      //  List<Product> products = (List<Product>) productRepository.findAll();

       // Page<Product> pr = productRepository.findAll(PageRequest.of(id,1));
//        System.out.println(pr);

     //   Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "Category"));
      //  List<Product> products = (List<Product>) productRepository.findAll(sort);
       // List<Product> products = (List<Product>) productRepository.findAll();
       // List<Product> products =  productRepository.findAllByVendorCode("00000001");
////
//        System.out.println(products);

//        List<Product> products = productRepository.findAllByVendorCode();
//        List<Product> products = new ArrayList<>();
//        products.add(productRepository.myQuery(1L));
//        Page<Product> pr = productRepository.findAll(PageRequest.of(1,2));
//        System.out.println(pr);

//        List<Product> products = new ArrayList<>();
        List<Product> products = productRepository.findAll();
//       // List<Product> products = productRepository.findAllByPriceBetween(1.0, 8.0);
//        Product product = productRepository.myQuery(1L);
//        products.add(product);

        return products.stream().collect(Collectors.toList());
    }

    public List<Product> getProductsByVendorCode(String code) {
        return productRepository.findAllByVendorCode(code);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }
}
