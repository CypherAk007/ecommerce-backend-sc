package com.project.ecomm.demo.Controllers;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.ExceptionDTO;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.service.FakeStoreProductService;
import com.project.ecomm.demo.service.ProductService;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ProductResponseDTO getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product =  productService.getProductById(id);
        return ProductResponseDTO.from(product);
    }



//    Instead of exposing the internal package flow in postman
//            we have beautified the error message now
//    @ExceptionHandler(NullPointerException.class)
//    public ExceptionDTO handleNullPointerException(){
//        ExceptionDTO exceptionDTO = new ExceptionDTO();
//        exceptionDTO.setStatus("Failure!!");
//        exceptionDTO.setMessage("Product Cannot Be Null!!");
//        return exceptionDTO;
//    }
}
