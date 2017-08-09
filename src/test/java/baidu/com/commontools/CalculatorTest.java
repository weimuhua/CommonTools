package baidu.com.commontools;

import org.junit.Before;
import org.junit.Test;

import baidu.com.commontools.utils.Calculator;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Wayne on 2017/7/31.
 */

public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setup() {
        mCalculator = new Calculator();
    }

    @Test
    public void testAdd() throws Exception {
        int sum = mCalculator.add(1, 2);
        assertEquals(3, sum);  //为了简洁，往往会static import Assert里面的所有方法。
    }

    @Test
    public void testMultiply() throws Exception {
        int product = mCalculator.multiply(2, 4);
        assertEquals(8, product);
    }
}
