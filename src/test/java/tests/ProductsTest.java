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
import pages.productspage;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class ProductsTest extends BaseTest {

    Loginpage login;
    productspage products;
    Cartpage cart;
    protected ExtentReports extent;
    protected ExtentTest test;
    String cartebutton="//a[normalize-space()='Cart']";
    String logoutbutton="//a[normalize-space()='Logout']";


    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginwithvaliddata(email, password);
        products = new productspage(page);
        products.gotoproductlist();
        cart=new Cartpage(page);
        extent= ExtentManager.getExtent();
        test=extent.createTest(method.getName());
    }

    @Test(priority = 0)
    public void testbluetopProduct() {
        products.searchforproduct("Blue Top");
        String prod = products.showproductname("Blue Top");
        Assert.assertEquals(prod, "Blue Top");
        String prodprice = products.showproductprice("Rs. 500");
        Assert.assertEquals(prodprice, "Rs. 500");

    }

    @Test(priority = 1)
    public void searchproductcategory() {
        String actualproductsresult1=products.searchformenJeans();
        Assert.assertEquals(actualproductsresult1,"Men - Jeans Products");
        Assert.assertEquals(page.url(),"https://automationexercise.com/category_products/6");
        String actualproductsresult2=products.searchformenTshirts();
        Assert.assertEquals(actualproductsresult2,"Men - Tshirts Products");
        Assert.assertEquals(page.url(),"https://automationexercise.com/category_products/3");
    }

        @Test(priority = 2)
        public void addingtwodifferentproducts () {
            products.addtwodifferentproductstocart();
            Locator cartbutton= page.locator(cartebutton);
            cartbutton.click();
            String cartemptyproductone=cart.emptycart();
            String cartemptyproducttwo=cart.emptycart();
            Assert.assertEquals(cartemptyproductone,"Cart is empty!");
            Assert.assertEquals(cartemptyproducttwo,"Cart is empty!");
        }

        @AfterMethod
        public void logout() {
            Locator log=page.locator(logoutbutton);
            log.click();
            extent.flush();
        }
}
