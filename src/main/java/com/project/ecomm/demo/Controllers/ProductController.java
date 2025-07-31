package com.project.ecomm.demo.Controllers;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.CreateFakeStoreProductRequestDTO;
import com.project.ecomm.demo.dtos.ExceptionDTO;
import com.project.ecomm.demo.dtos.FakeStoreRequestDTO;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.exceptions.ProductsNotFoundException;
import com.project.ecomm.demo.service.FakeStoreProductService;
import com.project.ecomm.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//Every thing sits on top of @components
@RestController //-> @Controller + @ResponseBody
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("productStorageService") ProductService productService){
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

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() throws ProductsNotFoundException {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDTO> productResponseDTOS =new ArrayList<>();
        for(Product product: products){
            productResponseDTOS.add(ProductResponseDTO.from(product));
        }
        return productResponseDTOS;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateFakeStoreProductRequestDTO createFakeStoreProductRequestDTO) throws ProductsNotFoundException {
        Product product = productService.createProduct(
                createFakeStoreProductRequestDTO.getName(),
                createFakeStoreProductRequestDTO.getDescription(),
                createFakeStoreProductRequestDTO.getPrice(),
                createFakeStoreProductRequestDTO.getImageUrl(),
                createFakeStoreProductRequestDTO.getCategory()
        );
        return new ResponseEntity<>(ProductResponseDTO.from(product), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> replaceProduct(@PathVariable("id") long id,
                                                             @RequestBody CreateFakeStoreProductRequestDTO createFakeStoreProductRequestDTO) throws ProductsNotFoundException {
        Product product = productService.replaceProduct(
                id,
                createFakeStoreProductRequestDTO.getName(),
                createFakeStoreProductRequestDTO.getDescription(),
                createFakeStoreProductRequestDTO.getPrice(),
                createFakeStoreProductRequestDTO.getImageUrl(),
                createFakeStoreProductRequestDTO.getCategory()
        );
        return new ResponseEntity<>(ProductResponseDTO.from(product), HttpStatus.CREATED);
    }


}
