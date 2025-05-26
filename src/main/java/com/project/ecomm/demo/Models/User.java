package com.project.ecomm.demo.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public abstract class User extends BaseModel{
    private String name;
    private String email;
    private String password;
}
