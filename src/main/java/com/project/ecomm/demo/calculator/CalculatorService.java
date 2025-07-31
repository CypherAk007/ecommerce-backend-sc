package com.project.ecomm.demo.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int add(int a,int b){
        System.out.println("Service: Logic before add");
        int result = a+b;
        System.out.println("Service: Logic after add");
        return result;
    }
}
