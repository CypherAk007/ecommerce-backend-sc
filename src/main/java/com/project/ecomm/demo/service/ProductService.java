package com.project.ecomm.demo.service;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;
}
//if we have multiple Classes implementing ProductService then ProductController throw error
//M1-> name one @Primary
//M2->
//Use @Qualifier in the constructor
//You must use the bean name, which by default is the class name with a lowercase first letter.
//So @Service public class OriginalStoreProductService becomes originalStoreProductService.