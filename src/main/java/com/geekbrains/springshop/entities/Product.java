package com.geekbrains.springshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "vendor_code")
    private String vendorCode;

//    @Column(name = "image")
//    private String imagePath;

    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "price")
    private double price;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
}
