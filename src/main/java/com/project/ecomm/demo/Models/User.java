package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
@Entity
public abstract class User extends BaseModel{
//    private String name;//take fm base class
    private String email;
    private String password;
}
