package com.project.ecomm.demo.service;

import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.dtos.FakeStoreRequestDTO;
import com.project.ecomm.demo.dtos.FakeStoreResponseDTO;
import com.project.ecomm.demo.dtos.ProductResponseDTO;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.exceptions.ProductsNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeStoreProductServiceTest {
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(restTemplate);

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        FakeStoreResponseDTO fakeStoreResponseDTODummy = getDummyFakeStoreResponseDTO();

        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeStoreResponseDTO.class)).thenReturn(fakeStoreResponseDTODummy);

//        FakeStoreResponseDTO fakeStoreResponseDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/1",
//                FakeStoreResponseDTO.class);

        Product product = fakeStoreProductService.getProductById(1);
        assertEquals(1L,product.getId());
        assertEquals("Product 1",product.getName());
        assertEquals("image.png.dummy",product.getImageUrl());
        assertEquals("Product 1 dummy",product.getDescription());
        assertEquals(120,product.getPrice());
        assertEquals("Category 1",product.getCategory().getName());

    }

    private FakeStoreResponseDTO getDummyFakeStoreResponseDTO(){
        FakeStoreResponseDTO fakeStoreResponseDTODummy = new FakeStoreResponseDTO();
        fakeStoreResponseDTODummy.setId(1L);
        fakeStoreResponseDTODummy.setTitle("Product 1");
        fakeStoreResponseDTODummy.setImage("image.png.dummy");
        fakeStoreResponseDTODummy.setDescription("Product 1 dummy");
        fakeStoreResponseDTODummy.setPrice(120);
        fakeStoreResponseDTODummy.setCategory("Category 1");
        return fakeStoreResponseDTODummy;
    }


    @Test
    public void testGetProductByIdThrowsExceptionOnValueNull() throws ProductNotFoundException {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeStoreResponseDTO.class)).thenReturn(null);

//        ACT & Assert
        assertThrows(ProductNotFoundException.class,()->fakeStoreProductService.getProductById(1L));
        //(Exception,Executable) -> here Executable is Functional interface.
    }

    @Test
    public void testCreateProductReturnsProduct(){
        FakeStoreResponseDTO dummyResponse = getDummyFakeStoreResponseDTO();

        FakeStoreRequestDTO fakeStoreRequestDTO = new FakeStoreRequestDTO();
        fakeStoreRequestDTO.setTitle("name");
        fakeStoreRequestDTO.setDescription("description");
        fakeStoreRequestDTO.setPrice(1.0);
        fakeStoreRequestDTO.setImage("imageUrl");
        fakeStoreRequestDTO.setCategory("category");

//        when(restTemplate.postForObject("https://fakestoreapi.com/products",
////CODE ISSUE -1 , OBJECTS MISMATCH
////                fakeStoreRequestDTO,
////             the issue with this is fakeStoreRequestDTO is new object
////                and when FakeStoreProductService.createProduct() is called the fakeStoreRequestDTO
////                there is a different object - so this test case will fail as 2 objects will not be same
//                any(),
//                FakeStoreResponseDTO.class)
//        ).thenReturn(dummyResponse);
//

//CODE ISSUE -2 , WHEN WE ARE PASSING any() in restTemplate then all other parameters also should be
//function call - so use eq()
        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products"),
                        any(),
                        eq(FakeStoreResponseDTO.class))
        ).thenReturn(dummyResponse);

        Product product = fakeStoreProductService.createProduct("Product 1","description",1.0,"imageUrl","category");

        assertEquals(1L,product.getId());
        assertEquals("Product 1",product.getName());
    }

    @Test
    public void testCreateProductReturnsProductVerifyAPICalls() {
        FakeStoreResponseDTO dummyResponse = getDummyFakeStoreResponseDTO();
        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreResponseDTO.class))
        ).thenReturn(dummyResponse);

        Product product = fakeStoreProductService.createProduct("Product 1","description",1.0,"imageUrl","category");

        verify(restTemplate,times(1)).postForObject(eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreResponseDTO.class));


    }
}
