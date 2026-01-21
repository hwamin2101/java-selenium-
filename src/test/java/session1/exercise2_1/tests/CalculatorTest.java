package session1.exercise2_1.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CalculatorTest {

    private int a;
    private int b;

    /* ===================== BEFORE ===================== */

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite - Setup test environment");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest - Prepare test execution");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass - Initialize CalculatorTest");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod - Reset test data");
        a = 10;
        b = 5;
    }

    /* ===================== TEST CASES ===================== */

    @Test(priority = 1, groups = {"smoke"})
    public void testAddition() {
        int result = a + b;
        Assert.assertEquals(result, 15, "Addition calculation is incorrect");
    }

    @Test(priority = 2, groups = {"smoke"})
    public void testSubtraction() {
        int result = a - b;
        Assert.assertEquals(result, 5, "Subtraction calculation is incorrect");
    }

    @Test(priority = 3, groups = {"regression"})
    public void testMultiplication() {
        int result = a * b;
        Assert.assertEquals(result, 50, "Multiplication calculation is incorrect");
    }

    @Test(priority = 4, groups = {"regression"}, dependsOnMethods = "testMultiplication")
    public void testDivision() {
        int result = a / b;
        Assert.assertEquals(result, 2, "Division calculation is incorrect");
    }

    // EDGE CASE + SoftAssert
    @Test(priority = 5, groups = {"edge"})
    public void testDivisionByZero() {
        SoftAssert softAssert = new SoftAssert();

        int x = 10;
        int y = 0;

        try {
            int result = x / y;
            softAssert.fail("Expected ArithmeticException but got result: " + result);
        } catch (ArithmeticException e) {
            softAssert.assertTrue(true, "ArithmeticException occurred as expected");
        }

        softAssert.assertAll();
    }

    /* ===================== AFTER ===================== */

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod - Cleanup after test method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass - Finish CalculatorTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest - Test execution completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite - Close test environment");
    }
}
