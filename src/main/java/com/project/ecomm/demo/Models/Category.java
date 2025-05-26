package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class Category extends BaseModel{
//    private long id;
    private String name;
}
