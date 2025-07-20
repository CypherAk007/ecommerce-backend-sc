package com.project.ecomm.demo.Controllers;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.service.FakeStoreProductService;
import com.project.ecomm.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Every thing sits on top of @components
@RestController //-> @Controller + @ResponseBody
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long id){
        Product product =  productService.getProductById(id);
        return ProductResponseDTO.from(product);
    }
}
