package src;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber(){

        Assert.assertEquals("Number is not equal 14", 14, getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Number should be more than 20", getClassNumber()>45);
    }
}