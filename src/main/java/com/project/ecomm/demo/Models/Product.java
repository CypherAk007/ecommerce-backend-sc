package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    private String name;//take fm base class
    @ManyToOne
    private Category category;//fk is created for category_id in Products table
    private String description;
//    private String unitOfMeasure;
//    private String url;
//    private Double rating;
    private String imageUrl;
    private Double price;

}
