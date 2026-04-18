package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class checkoutpage extends SignupPage {

    String viewdcheckoutetails = "//table[@class='table table-condensed']";
    String cardename="//input[@name='name_on_card']";
    String cardenumber="//input[@name='card_number']";
    String cardecvc="//input[@name='cvc']";
    String cardeexpiremonth="//input[@name='expiry_month']";
    String cardeexpireyear="//input[@name='expiry_year']";
    String submit="//button[@id='submit']";


    public checkoutpage(Page page) {
        super(page);
    }

    public String vieworderdetails() {
        String totalpriceval="";
        Locator checkoutdetail = page.locator(viewdcheckoutetails);
        boolean checkoutdetailfound = checkoutdetail.isVisible();
         System.out.println("are the checkout details displayed "+checkoutdetailfound);
        if (checkoutdetailfound) {
            Locator checoutdescription = checkoutdetail.locator(".cart_description");
            String checkoutdesc = checoutdescription.innerText();
            System.out.println("checkout product description: " + checkoutdesc);
            Locator totalprice = checkoutdetail.locator(".cart_total");
             totalpriceval = totalprice.innerText();
            System.out.println("The checkout total price is " + totalpriceval);
        }
        return totalpriceval;
    }


    public boolean placeorder(){
        Locator placeorderbutton=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Place Order"));
        placeorderbutton.click();
        Locator paymentdetails=page.getByText("Pay and Confirm Order", new Page.GetByTextOptions().setExact(true));
        boolean paymentdetailsfound = paymentdetails.isVisible();
        return paymentdetailsfound;
    }

    public void completepaymentproceess(String name,String cardnumber,int cvc,int expirationmonth,int expirationyear){
         Locator namedetails=page.locator(cardename);
         namedetails.fill(name);
         Locator cardnumberdetails=page.locator(cardenumber);
         cardnumberdetails.fill(cardnumber);
         Locator cvcdetails=page.locator(cardecvc);
         cvcdetails.fill(String.valueOf(cvc));
         Locator expiredmonth=page.locator(cardeexpiremonth);
         expiredmonth.fill(String.valueOf(expirationmonth));
         Locator expiredyear=page.locator(cardeexpireyear);
         expiredyear.fill(String.valueOf(expirationyear));
         Locator payandconfirm=page.locator(submit);
         payandconfirm.click();
         Locator ordersuccessmessage=page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Order Placed!"));
         String ordermesaage=ordersuccessmessage.textContent();
         System.out.println(ordermesaage);
    }
}
