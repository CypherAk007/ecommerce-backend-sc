package com.project.ecomm.demo.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeStoreProductRequestDTO {
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
}
