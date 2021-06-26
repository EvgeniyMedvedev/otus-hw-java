package ru.otus;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.otus.interceptor.Interceptor;
import ru.otus.service.CalcService;

/**
 * AnnotationLogTest.
 *
 * @author Evgeniy_Medvedev
 */

public class AnnotationLogTest {

    CalcService calc = Interceptor.createCalc();

    @Test
    public void calcTest(){
        var x = 3;
        var y = 2;

        long response = calc.sum(x, y);

        assertEquals(5, response);
    }

    @Test
    public void calcTestWithString(){
        var msg = "Hi";
        var x = 3;
        var y = 2;
        var expected = String.format("\nMessage: %s, \nSum: %s\n", msg, x + y);

        String response = calc.sum(x, y, msg);

        assertEquals(expected, response);
    }

    @Test
    public void squareTest(){
        var x = 3;

        long response = calc.square(x);

        assertEquals(x * x, response);
    }

    @Test
    public void calcWithoutAnnotationTest(){
        var x = 3;
        var y = 2;

        var exp = String.valueOf(x + y);

        String response = calc.sumWithoutAnnotation(x, y);

        assertEquals(exp, response);
    }
}