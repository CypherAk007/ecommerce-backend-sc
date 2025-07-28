package com.project.ecomm.demo.service;

import com.project.ecomm.demo.Models.Category;
import com.project.ecomm.demo.Models.Product;
import com.project.ecomm.demo.exceptions.ProductNotFoundException;
import com.project.ecomm.demo.exceptions.ProductsNotFoundException;
import com.project.ecomm.demo.repositories.CategoryRepository;
import com.project.ecomm.demo.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productStorageService")
public class ProductStorageService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductStorageService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
//        Optional<Product> productOptional = productRepository.getProductById(id);
//        if(productOptional.isEmpty()){
//            throw new ProductNotFoundException("Product With id: "+id+" Not Found!!");
//        }
//        Product product = productOptional.get();
//        return product;


//        TEST OUT THE NEW QUERRIES FOR THIS ENDPOINT

////        List<Product> findByCategory(Category category);
//        Optional<Category> categoryOptional = categoryRepository.findByName("electronics");
//        List<Product> products = productRepository.findByCategory(categoryOptional.get());

////       List<Product> findByCategory_Name(String Name);
//        List<Product> products = productRepository.findByCategory_Name("electronics");
//        System.out.println(products);

////        @Query("select p from Product p where p.category.name=:categoryName")
////        List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);
//        List<Product> products = productRepository.getProductByCategoryName("electronics");
//        System.out.println(products);

        List<Product> products = productRepository.getProductByCategoryNameNative("electronics");
        System.out.println(products);
        return null;

    }

    @Override
    public List<Product> getAllProducts() throws ProductsNotFoundException {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String description, double price, String imageUrl, String category) {
        Product product = new Product();
        buildProduct( product, name,  description,  price,  imageUrl,  category);
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String category) {
        Optional<Category> categoryFmDB = categoryRepository.findByName(category);
        if(categoryFmDB.isEmpty()){
            Category categoryObj = new Category();
            categoryObj.setName(category);
            return categoryRepository.save(categoryObj);
        }
        Category categoryObjDB = categoryFmDB.get();
        return categoryObjDB;
    }

    @Override
    public Product replaceProduct(long id, String name, String description, double price, String imageUrl, String category) {
        Product product = new Product();
        product.setId(id);//if id is present the .save() acts like PUT if absent POST
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

//        Category categoryObj = new Category();//for evey product new catogery is created which is wrong
//        reuse the already created category

        Category categoryObj = getCategoryFromDB(category);
        product.setCategory(categoryObj);
        return productRepository.save(product);
    }

    private Product buildProduct(Product product,String name, String description, double price, String imageUrl, String category){
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

//        Category categoryObj = new Category();//for evey product new catogery is created which is wrong
//        reuse the already created category

        Category categoryObj = getCategoryFromDB(category);
        product.setCategory(categoryObj);
        return product;
    }
}
