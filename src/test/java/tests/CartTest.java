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
        Assert.assertEquals(actualproductname,"Blue Top");
        String actualproductprice=products.viewproductprice();
        Assert.assertEquals(actualproductprice,"Rs. 500");
        products.addbluetoptocart();
        cartpage.viewcartlist();
        int cartsize=cartpage.viewcartoneproductsize();
        System.out.println("the cart size "+ cartsize);
        Assert.assertEquals(cartsize,1);
        String actualcartname=cartpage.viewcartproductname();
        Assert.assertEquals(actualcartname,actualproductname);
        String actualcartprice=cartpage.viewcartproductprice();
        Assert.assertEquals(actualcartprice,actualproductprice);
        String cartemptyproductone=cartpage.emptycart();
        Assert.assertEquals(cartemptyproductone,"Cart is empty!");
    }

    @Test(priority = 1)
    public void CartTesttwosameproduct(){
        products.viewbluetopproduct();
        String cartsuccessmessage=products.addtwotbluetoptocart(2);
        Assert.assertEquals(cartsuccessmessage,"Your product has been added to cart.");
        cartpage.viewcartlist();
        String cartprice=cartpage.checkbluetopprouctprice();
        //Assert.assertEquals(cartprice,"Rs. 1000");
        String cartemptyproductone=cartpage.emptycart();
        Assert.assertEquals(cartemptyproductone,"Cart is empty!");
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
        String cartemptyproductone=cartpage.emptycart();
        String cartemptyproducttwo=cartpage.emptycart();
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
