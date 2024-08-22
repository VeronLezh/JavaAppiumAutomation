package src;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString(){
        Assert.assertTrue("String should contain substring 'hello' or 'Hello'",getClassString().contains("hello") || getClassString().contains("Hello"));

    }

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("Number is not equal 14", 14, getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Number should be more than 20", getClassNumber()>45);
    }
}