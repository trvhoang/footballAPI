package features.test;


import application.Calculation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculationTest {
    @BeforeMethod
    void setup(){
        System.out.println("START TEST");
    }

    @DataProvider (name = "sum")
    Object[][] sumdata(){
        return new Object[][]{
                new Object[] {1,1,2},
                new Object[] {2,2,4},
                new Object[] {4,1,5},
                new Object[] {5,5,11},
        };
    }

    @Test(dataProvider = "sum")
    void sumTest(int a, int b, int expectedResult) {
//        System.out.println(c);
        Assert.assertEquals(Calculation.sum(a,b),expectedResult);

    }


    @Test
    void subTest(){
        Assert.assertEquals(Calculation.sub(4,3), 1);
    }

    @Test
    void mulTest() {
        Assert.assertEquals(Calculation.mul(2,3), 6);
    }

    @AfterMethod
    void setup2(){
        System.out.println("STOP TEST");
    }

}
