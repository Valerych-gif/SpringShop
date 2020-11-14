package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.Product;
import com.geekbrains.springshop.entities.ProductImage;
import com.geekbrains.springshop.services.CategoryService;
import com.geekbrains.springshop.services.ImageSaverService;
import com.geekbrains.springshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ProductController {

    private final String DEFAULT_PRODUCT_IMG = "default-product-img.png";
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        String imgSrc = !product.getImages().isEmpty()?product.getImages().get(0).getPath():DEFAULT_PRODUCT_IMG;
        model.addAttribute("product", product);
        model.addAttribute("imgSrc", imgSrc);
        return "shop/product";
    }

    @GetMapping("/product/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest){
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

}
