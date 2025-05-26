package com.project.ecomm.demo.dtos;

import com.project.ecomm.demo.Models.Category;
import com.project.ecomm.demo.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setName(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category category1 = new Category();
        category1.setName(category);

        product.setCategory(category1);

        return product;
    }

}
