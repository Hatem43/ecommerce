package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Cartpage extends SignupPage {

    String updatedprice="(//p[@class='cart_total_price'])[1]";
    String cart="//tbody//tr";
    String carttable="#product-1";
    String emptybutton="(//i[@class='fa fa-times'])[1]";
    String emptymessage="//b[normalize-space()='Cart is empty!']";
    String bluetoproduct="//a[normalize-space()='Blue Top']";
    String cartprice="(//p[@class='cart_total_price'])[1]";

    public Cartpage(Page page) {
        super(page);
    }

    public String checkbluetopprouctprice(){

        Locator price=page.locator(updatedprice);
        String pricevalue=price.textContent();
        return pricevalue;
    }

    public boolean viewcartlist(){

        Locator cartelement=page.locator(carttable);
        boolean cart=cartelement.isVisible();
        return cart;
    }

    public int viewcartoneproductsize(){
        Locator cartsize=page.locator(cart);
        int size=cartsize.count();
        return size;
    }

    public String viewcartproductname(){
        Locator cartproductname=page.locator(bluetoproduct);
        String cartprodname=cartproductname.textContent();
        return cartprodname;
    }

    public String viewcartproductprice(){
        Locator cartproductprice=page.locator(cartprice);
        String cartprodprice=cartproductprice.textContent();
        return cartprodprice;
    }

    public int viewcarttwoproducts(){
        Locator sa=page.locator(cart);
        int cartsize=sa.count();
        return cartsize;
    }

    public void testtwoproductscart(){
        Locator tablerows= page.locator("//tbody//tr");
        for(int i=0;i<tablerows.count();i++){
            Locator row=tablerows.nth(i);
            System.out.println("Row " + i);
            String productcartdescription=row.locator(".cart_description").innerText();
            System.out.println("the description is "+productcartdescription);
            String productcartprice=row.locator(".cart_price").innerText();
            System.out.println("the price is "+productcartprice);
            int productcartquantity=Integer.parseInt(row.locator(".cart_quantity").innerText());
            System.out.println("the product quantity is "+productcartquantity);
            String total = row.locator(".cart_total").innerText();
            System.out.println("the total price is "+total);
        }

    }

    public void proceedtocheckout(){

    Locator checkoutbutton=page.getByText("Proceed To Checkout", new Page.GetByTextOptions().setExact(true));
    checkoutbutton.click();
    }

    public String emptycart(){
        Locator empty=page.locator(emptybutton);
        empty.click();
        Locator carttext=page.locator(emptymessage);
        String emptyinfo=carttext.textContent();
        return emptyinfo;
    }

}
