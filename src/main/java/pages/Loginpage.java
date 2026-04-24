package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;



public class Loginpage extends SignupPage{

    private String emailAddress="//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]";
    private String gotologin="//a[normalize-space()='Signup / Login']";

    public Loginpage(Page page) {
        super(page);
    }

    public void loginwithvaliddata(String email,String password){
       Locator log=page.locator(gotologin);
        log.click();
        page.fill(emailAddress,email);
        Locator pass=page.getByPlaceholder("Password");
        pass.fill(password);
        Locator login=page.getByText("Login", new Page.GetByTextOptions().setExact(true));
        login.click();
    }

    public void loginwithinvaliddata(String email,String password){
        page.fill(emailAddress,email);
        Locator pass=page.getByPlaceholder("Password");
        pass.fill(password);
        Locator login=page.getByText("Login", new Page.GetByTextOptions().setExact(true));
        login.click();

    }
}
