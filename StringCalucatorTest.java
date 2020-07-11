package com.info.opsramp;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringCalucatorTest {

	@Autowired
	private StringCalculator calObject;

	@Test
    public void emptyStringShouldReturnZero() {
       assertEquals(0, calObject.calculate(""));
    }
   @Test
    public void oneNumberShouldReturnItseft() {
        assertEquals(3, calObject.calculate("3"));
    }
    @Test
    public void twoNumbersShouldBeAdded() {
        assertEquals(7, calObject.calculate("2,5"));
    }
    @Test
    public void moreDigitsSupported() {
        assertEquals(77, calObject.calculate("22,55"));
    }
    @Test
    public void allowNnumersAsInput() {
        assertEquals(45, calObject.calculate("1,2,3,4,5,6,7,8,9"));
    }
    @Test
    public void supportNewLineAsSeparator() {
        assertEquals(6, calObject.calculate("1\n2,3"));
    }
   @Test
    public void supportForCustomDelimiter() {
        assertEquals(10, calObject.calculate(";\n1;2;3;4"));
    }
    
    @Test
    public void negativeNotSupported() {
        try {
            calObject.calculate("1,4");
            //fail("exception should have been thrown");
        }
        catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed -1", e.getMessage());
        }
    }

}
