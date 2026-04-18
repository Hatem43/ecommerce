package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignupPage {

    protected Page page;
    private String name="//input[@placeholder='Name']";
    private String email="//input[@data-qa='signup-email']";
    private String signup="//button[normalize-space()='Signup']";
    private String loginsignup="//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a";
    private String malegender="//input[@id='id_gender1']";
    private String birthday="//select[@id='days']";
    private String birthmonth="//select[@id='months']";
    private String birthyear="//select[@id='years']";
    private String acceptred="(//input[@id='newsletter'])[1]";
    private String country="//select[@id='country']";
    private String zipcode="//input[@id='zipcode']";

    public SignupPage(Page page) {
        this.page = page;
    }

    public void gotosignuppage() {
        page.click(loginsignup);
    }

    public void enteraccountinformation(String username, String emailaddress,String pass,String day,String month,String year) {
        page.fill(name,username);
        page.fill(email,emailaddress);
        page.click(signup);
        page.click(malegender);
        Locator password=page.getByLabel("Password *");
        password.fill(pass);
        page.selectOption(birthday,day);
        page.selectOption(birthmonth,month);
        page.selectOption(birthyear,year);
        page.check(acceptred);
        Locator reciveoffers=page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Receive special offers from"));
        reciveoffers.check();
    }

    public void enteraddressinformation(String Firstname,String Lastname,String Company,String Address,String Country,String State,String City,String Zipcode,String mobilephone) {
        Locator firstname=page.getByLabel("First name *");
        firstname.fill(Firstname);
        Locator lastname=page.getByLabel("Last name *");
        lastname.fill(Lastname);
        Locator comp=page.getByLabel("Company", new Page.GetByLabelOptions().setExact(true));
        comp.fill(Company);
        Locator address=page.getByLabel("Address * (Street address, P.O. Box, Company name, etc.)");
        address.fill(Address);
        page.selectOption(country,Country);
        Locator state=page.getByLabel("State *");
        state.fill(State);
        Locator city=page.getByLabel("City *");
        city.fill(City);
        page.fill(zipcode,Zipcode);
        Locator mobile=page.getByLabel("Mobile Number *");
        mobile.fill(mobilephone);
        Locator createaccount=page.getByText("Create Account", new Page.GetByTextOptions().setExact(true));
        createaccount.click();
    }
}


