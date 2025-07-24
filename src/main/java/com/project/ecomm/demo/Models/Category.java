package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Entity
@Getter
@Setter
@Entity
public class Category extends BaseModel{
//    private long id;
//    private String name;//take fm base class
    @OneToMany(mappedBy = "category")// by writing mappedBy we add forign key
    List<Product> products;

    //now total 4 tables
    // category | products | prdouctsTable-CategoryTable(products)|
//    prdouctsTable-CategoryTable(featureProducts)|
    // are created as spring dont know
//    which List<Product> products or featureProducts
//    to map to for @ManyToOne private Category category ->fm products class;
//    so only we use mappedBy

    @OneToMany
    List<Product> featureProducts;
}
