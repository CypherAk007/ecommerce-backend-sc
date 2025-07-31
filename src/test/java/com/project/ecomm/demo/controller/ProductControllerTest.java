package com.project.ecomm.demo.controller;

import com.project.ecomm.demo.Controllers.ProductController;
import com.project.ecomm.demo.Models.Category;
import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @MockitoBean
    //WE NEED TO NOTIFY WHICH CONFLICTING SERVICE BEAN TO USE(FakeStoreProductService/ProductStorageService)
    @Qualifier("productStorageService")
    ProductService productService;

    @Autowired
    ProductController productController;

    @Test
    public void testProductByIdReturnsDTO() throws ProductNotFoundException {
//        Arrange
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("Product 1");
        dummyProduct.setDescription("Product 1");
        dummyProduct.setImageUrl("img.png.dummy");
        dummyProduct.setPrice(100.5);

        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyCategory.setName("Category 1");

        dummyProduct.setCategory(dummyCategory);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);



        ProductResponseDTO productResponseDTO = productController.getProductById(1L);
//        ACT
        assertEquals(1L,productResponseDTO.getId());
        assertEquals("Product 1",productResponseDTO.getName());
        assertEquals("Product 1",productResponseDTO.getDescription());
        assertEquals("img.png.dummy",productResponseDTO.getImageUrl());
        assertEquals(100.5,productResponseDTO.getPrice());
        assertEquals("Category 1",productResponseDTO.getCategory());
    }

    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException {
        when(productService.getProductById(1L)).thenReturn(null);

        ProductResponseDTO productResponseDTO = productController.getProductById(1L);

        assertEquals(null,productResponseDTO);
    }
}