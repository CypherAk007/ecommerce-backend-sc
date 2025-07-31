package com.project.ecomm.demo.calculator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculatorControllerTest {

    @Autowired
    CalculatorController calculatorController;

//    private  CalculatorService calculatorService = Mockito.mock(CalculatorService.class);
    @MockitoBean
    CalculatorService calculatorService;

//    public CalculatorControllerTest(CalculatorController calculatorController) {
//        this.calculatorController = calculatorController;
//    }

    @Test
    public void testSumAcceptsTwoIntegerReturnsSumAsInteger(){
        when(calculatorService.add(anyInt(),anyInt())).thenReturn(1000);
        when(calculatorService.add(5,10)).thenReturn(15);

//    arrange
        int a = 5;
        int b = 10;
        int expectedResult = 15;

//    act
        int actualResult = calculatorController.sum(a,b);

//    assert
        assertEquals(expectedResult,actualResult);

    }
}
