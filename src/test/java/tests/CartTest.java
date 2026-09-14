package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cartpage;
import pages.Loginpage;
import pages.productspage;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class CartTest extends BaseTest {

    productspage products;
    Loginpage login;
    String logoutbutton="//a[normalize-space()='Logout']";
    protected ExtentReports extent;
    protected ExtentTest test;
    Cartpage cartpage;

    @BeforeMethod
    public void beforeMethod(Method method){
        login=new Loginpage(page);
        login.loginwithvaliddata(email,password);
        products=new productspage(page);
        products.gotoproductlist();
        extent= ExtentManager.getExtent();
        test=extent.createTest(method.getName());
        cartpage =new Cartpage(page);
    }

    @Test (priority = 0)
    public void carttestproduct(){
        products.viewbluetopproduct();
        String actualproductname=products.viewproductname();
        System.out.println("the product name is " +actualproductname);
        Assert.assertEquals(actualproductname,"Blue Top");
        String actualproductprice=products.viewproductprice();
        System.out.println("the product price is " +actualproductprice);
        Assert.assertEquals(actualproductprice,"Rs. 500");
        products.addbluetoptocart();
        boolean actual=cartpage.viewcartlist();
        Assert.assertTrue(actual);
        int cartsize=cartpage.viewcartoneproductsize();
        System.out.println("the cart size is "+ cartsize);
        Assert.assertEquals(cartsize,1);
        String actualcartname=cartpage.viewcartproductname();
        System.out.println("the product cart name is " +actualcartname);
        Assert.assertEquals(actualcartname,actualproductname);
        String actualcartprice=cartpage.viewcartproductprice();
        System.out.println("the product cart price is " +actualcartprice);
        Assert.assertEquals(actualcartprice,actualproductprice);
        boolean cartemptyproduct=cartpage.emptycart();
        Assert.assertTrue(cartemptyproduct);
    }

    @Test(priority = 1)
    public void CartTesttwosameproduct(){
        products.viewbluetopproduct();
        String cartsuccessmessage=products.addtwotbluetoptocart(2);
        Assert.assertEquals(cartsuccessmessage,"Your product has been added to cart.");
        boolean actual=cartpage.viewcartlist();
        Assert.assertTrue(actual);
        String cartprice=cartpage.checkbluetopprouctprice();
        System.out.println("the product cart price is "+cartprice);
        Assert.assertEquals(cartprice,"Rs. 1000");
        boolean cartemptyproduct=cartpage.emptycart();
        Assert.assertTrue(cartemptyproduct);
    }

    @Test(priority = 2)
    public void CartTesttwodifferentproduct() {
        products.addtwodifferentproductstocart();
        Cartpage cartpage = new Cartpage(page);
        cartpage.viewcartlist();
        int cartsize = cartpage.viewcarttwoproducts();
        System.out.println("the cart size is " + cartsize);
        Assert.assertEquals(cartsize, 2);
        cartpage.testtwoproductscart();
        boolean cartemptyproducts=cartpage.emptycart();
        Assert.assertTrue(cartemptyproducts);
    }


    @AfterMethod
    public void logout(ITestResult result) {
        Locator log=page.locator(logoutbutton);
        log.click();

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        }
        else {
            test.skip("Test Skipped");
        }
        extent.flush();
    }
}
