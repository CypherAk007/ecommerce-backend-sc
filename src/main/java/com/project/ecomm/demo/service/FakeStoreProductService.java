package com.project.ecomm.demo.service;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.FakeStoreRequestDTO;
import com.project.ecomm.demo.dtos.FakeStoreResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotCreatedException;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.exceptions.ProductNotUpdatedException;
import com.project.ecomm.demo.exceptions.ProductsNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service("fakeStoreProductService")
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
        if(fakeStoreResponseDTO==null) {
            throw new ProductNotFoundException("Product with id: "+id+" not found!!");
        }
        return fakeStoreResponseDTO.toProduct();


    }

    public List<Product> getAllProducts() throws ProductsNotFoundException {
        FakeStoreResponseDTO[] fakeStoreResponseDTOS = restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreResponseDTO[].class);
        if(fakeStoreResponseDTOS==null){
            throw new ProductsNotFoundException("Fetch All Products not found!!");
        }
        List<Product> products = new ArrayList<>();
        for(FakeStoreResponseDTO fakeStoreResponseDTO:fakeStoreResponseDTOS){
            Product product = fakeStoreResponseDTO.toProduct();
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(String name,String description,double price,String imageUrl,String category) {
        FakeStoreRequestDTO fakeStoreRequestDTO = createDTOFromParams(name,description,price,imageUrl,category);
        FakeStoreResponseDTO fakeStoreResponseDTO = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreRequestDTO,FakeStoreResponseDTO.class);
        if(fakeStoreResponseDTO==null){
            throw new ProductNotCreatedException("Unable to Create Product with name: "+name);
        }
        return fakeStoreResponseDTO.toProduct();
    }

    @Override
    public Product replaceProduct(long id, String name, String description, double price, String imageUrl, String category) {
        FakeStoreRequestDTO updateFakeStoreRequestDTO =  createDTOFromParams(name,description,price,imageUrl,category);
//        exchange() -> put returns void but we need response for put so use Exchange()
//        exchange works with entities not raw objects -> takes http entity and returns response entity
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreRequestDTO> requestEntity = new HttpEntity<>(
                updateFakeStoreRequestDTO,httpHeaders
        );

        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreResponseDTO.class
        );

        if(responseEntity.getBody()==null){
            throw new ProductNotUpdatedException("Unable to Update Product with id: "+id);
        }

        return responseEntity.getBody().toProduct();
    }

    private FakeStoreRequestDTO createDTOFromParams(String name, String description,double price, String imageUrl,String category){
        FakeStoreRequestDTO fakeStoreRequestDTO = new FakeStoreRequestDTO();
        fakeStoreRequestDTO.setTitle(name);
        fakeStoreRequestDTO.setDescription(description);
        fakeStoreRequestDTO.setPrice(price);
        fakeStoreRequestDTO.setImage(imageUrl);
        fakeStoreRequestDTO.setCategory(category);
        return fakeStoreRequestDTO;
    }
}