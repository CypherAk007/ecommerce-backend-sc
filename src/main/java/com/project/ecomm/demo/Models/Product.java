package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private Category category;
    private String description;
//    private String unitOfMeasure;
//    private String url;
//    private Double rating;
    private String imageUrl;
    private Double price;

}
