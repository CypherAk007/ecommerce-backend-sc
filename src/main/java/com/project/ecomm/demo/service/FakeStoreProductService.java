package com.project.ecomm.demo.service;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.FakeStoreResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        FakeStoreResponseDTO fakeStoreResponseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreResponseDTO.class);
        if(fakeStoreResponseDTO==null){
            throw new ProductNotFoundException("Product with id: "+id+" not found!!");
        }
        return fakeStoreResponseDTO.toProduct();


    }
}