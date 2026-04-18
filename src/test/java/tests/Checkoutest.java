package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cartpage;
import pages.Loginpage;
import pages.checkoutpage;
import pages.productspage;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class Checkoutest extends BaseTest {

    productspage products;
    Loginpage login;
    String logoutbutton = "//a[normalize-space()='Logout']";
    protected ExtentReports extent;
    protected ExtentTest test;
    Cartpage cartpage;
    checkoutpage checkout;

    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginwithvaliddata(email, password);
        products = new productspage(page);
        products.gotoproductlist();
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        cartpage = new Cartpage(page);
        checkout = new checkoutpage(page);
    }

    @Test
    public void checkoutest() {
        products.viewbluetopproduct();
        String actualproductname = products.viewproductname();
        Assert.assertEquals(actualproductname, "Blue Top");
        String actualproductprice = products.viewproductprice();
        Assert.assertEquals(actualproductprice, "Rs. 500");
        products.addbluetoptocart();
        cartpage.viewcartlist();
        cartpage.proceedtocheckout();
        String checkouttotalprice=checkout.vieworderdetails();
        Assert.assertEquals(checkouttotalprice,actualproductprice);
        Boolean paymentdetails=checkout.placeorder();
        System.out.println("are the payment details dispalyed "+paymentdetails);
        Assert.assertTrue(paymentdetails);
        checkout.completepaymentproceess("HatemTest","4111 1111 1111 1111",123,12,2030);
    }


    @AfterMethod
    public void logout() {

       Locator log=page.locator(logoutbutton);
       log.click();
        extent.flush();
    }
}
