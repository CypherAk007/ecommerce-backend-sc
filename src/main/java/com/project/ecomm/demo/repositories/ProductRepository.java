package com.project.ecomm.demo.repositories;

import com.project.ecomm.demo.Models.Category;
import com.project.ecomm.demo.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface  ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> getProductById(long id);

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_Name(String Name);

    @Query("select p from Product p where p.category.name=:categoryName")
    List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = CustomQuery.GET_PRODUCT_FROM_CATEGORY_NAME,nativeQuery = true)
    List<Product> getProductByCategoryNameNative(@Param("categoryName") String categoryName);

}
