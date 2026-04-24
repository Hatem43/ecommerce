package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Loginpage;
import pages.SignupPage;
import utils.ExtentManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest extends BaseTest {

    SignupPage signup;
    Loginpage login;
    protected ExtentReports extent;
    protected ExtentTest test;


    @BeforeMethod
    public void setuptest(Method method) throws IOException, ParseException {
        extent= ExtentManager.getExtent();
        test=extent.createTest(method.getName());
        signup=new SignupPage(page);
        signup.gotologinandsignuppage();

    }

/*
    @Test
    public void signuptestwithvalidcredentials() {
        signup.enteraccountinformation(name,email,password,day,month,year);
        signup.enteraddressinformation(firstnam,lastnam,comp,address,count,stat,cit,zip,mob);
    }

 */

    @Test
    public void logintestwithvaliddata() {
        login=new Loginpage(page);
        login.loginwithvaliddata(email,password);
        Assert.assertEquals(page.url(),"https://automationexercise.com/");
    }

    @Test
    public void logintestwithinvaliddata() {
        login=new Loginpage(page);
        login.loginwithinvaliddata("hesso","21212s");
        Assert.assertEquals(page.url(),"https://automationexercise.com/login");
    }

    @AfterMethod
    public void screen(){
        extent.flush();
    }

}
