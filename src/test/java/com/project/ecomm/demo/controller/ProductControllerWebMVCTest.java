package com.project.ecomm.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecomm.demo.Controllers.ProductController;
import com.project.ecomm.demo.Models.Category;
import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.FakeStoreResponseDTO;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @MockitoBean
    @Qualifier("productStorageService")
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Product getProductForTest(){
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("Product 1");
        dummyProduct.setImageUrl("image.png.dummy");
        dummyProduct.setDescription("Product 1 dummy");
        dummyProduct.setPrice(1.1);

        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyProduct.setCategory(dummyCategory);


        return dummyProduct;
    }

    @Test
    public void testGetAllProductsRunsSuccessfully() throws Exception{
        //arrange
        Product dummyProduct1 = getProductForTest();
        Product dummyProduct2 = getProductForTest();
        dummyProduct2.setId(2);

        List<Product> dummyProducts = List.of(dummyProduct1,dummyProduct2);
        List<ProductResponseDTO> dummyProductResponseDTOS = List.of(ProductResponseDTO.from(dummyProduct1),ProductResponseDTO.from(dummyProduct2));

        when(productService.getAllProducts()).thenReturn(dummyProducts);

//        Act & Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(dummyProductResponseDTOS)));

    }
}
