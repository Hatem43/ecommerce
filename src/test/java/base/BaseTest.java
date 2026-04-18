package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BaseTest{

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected String name;
    protected String email;
    protected String password;
    protected String day;
    protected String month;
    protected String year;
    protected String firstnam;
    protected String lastnam;
    protected String comp;
    protected String address;
    protected String count;
    protected String stat;
    protected String cit;
    protected String zip;
    protected String mob;


    @BeforeSuite
    public void setup() throws IOException, ParseException {
        playwright = Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page=browser.newPage();
        page.navigate("https://automationexercise.com/");
        page.click("//a[normalize-space()='Signup / Login']");
        FileReader f=new FileReader("C:\\Users\\l e n o v o\\IdeaProjects\\playwrightproject\\src\\test\\resources\\Info.json");
        JSONParser j=new JSONParser();
        Object o=j.parse(f);
        JSONObject Info=(JSONObject) o;
        name=(String)Info.get("username");
        email=(String)Info.get("email");
        password=(String)Info.get("password");
        day=(String)Info.get("day");
        month=(String)Info.get("month");
        year=(String)Info.get("year");
        firstnam=(String)Info.get("first");
        lastnam=(String)Info.get("last");
        comp=(String)Info.get("company");
        address=(String)Info.get("Address");
        count=(String)Info.get("country");
        stat=(String)Info.get("state");
        cit=(String)Info.get("city");
        zip=(String)Info.get("zipcode");
        mob=(String)Info.get("mobile");

    }

    @AfterSuite
    public void teardown() {

        if (browser!=null) {
            browser.close();
        }
        if (playwright!=null) {
            playwright.close();
        }
    }
}
