package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class productspage extends SignupPage {

    String productlist = "(//a[@href='/products'])[1]";
    String blueTopcart = "(//button[normalize-space()='Add to cart'])[1]";
    String Menshirtcart = "//button[normalize-space()='Add to cart']";
    String product = "//h2[normalize-space()='Blue Top']";
    String produtprice = "//span[normalize-space()='Rs. 500']";
    String productsdetails = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]";
    String productimage = "//div[@class='view-product']//img[@alt='ecommerce website products']";
    String cartmssg = "//p[normalize-space()='Your product has been added to cart.']";
    String mencategory = "//a[normalize-space()='Men']";
    String bluetopproductdetails="//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]";
    String viewbluetopcart="//u[normalize-space()='View Cart']";
    String bluetopproduct="//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]";
    String menshirtproduct="//div[3]//div[1]//div[2]//ul[1]//li[1]//a[1]";
    String searchresultproduct="//div[@class='features_items']//div[@class='col-sm-4']";
    String searchproductsbutton="#submit_search";
    String blueetopproduct="//h2[normalize-space()='Blue Top']";
    String blueetopproductprice="//span[normalize-space()='Rs. 500']";


    public productspage(Page page) {
        super(page);
    }

    public void gotoproductlist() {
        Locator products = page.locator(productlist);
        products.click();
    }

    public void searchforproduct(String productname) {
        Locator search = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search Product"));
        search.fill(productname);
        Locator searchbutton = page.locator(searchproductsbutton);
        searchbutton.click();
        Locator searchedproduct=page.locator(searchresultproduct);
        boolean searchresult=searchedproduct.isVisible();
        System.out.println(" is the searchedproduct visible?" + searchresult);
        Locator proddetails = page.locator(productsdetails);
        proddetails.click();
    }

    public String showproductname(String productname) {
        boolean productimg = page.locator(productimage).isVisible();
        if (productimg == true) {
            Locator prd = page.locator(product);
            productname = prd.textContent();
        }
        return productname;
    }

    public String showproductprice(String productprice) {
        boolean productimg = page.locator(productimage).isVisible();
        if (productimg == true) {
            Locator prdprice = page.locator(produtprice);
            productprice = prdprice.textContent();
        }
        return productprice;
    }

    public String searchformenTshirts() {
        Locator mencateg = page.locator(mencategory);
        mencateg.click();
        Locator tshirt = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Tshirts"));
        tshirt.click();
        Locator tshirstlistheading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Men - Tshirts Products"));
        Boolean cateoryheading = tshirstlistheading.isVisible();
        System.out.println("is the category heading visible" + cateoryheading);
        String tshirstlistname = tshirstlistheading.textContent();
        return tshirstlistname;
    }


    public String searchformenJeans() {
        Locator mencateg = page.locator(mencategory);
        mencateg.click();
        Locator jeans = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Jeans"));
        jeans.click();
        Locator jeanslistheading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Men - Jeans Products"));
        String jeanslistname = jeanslistheading.textContent();
        return jeanslistname;
    }

    public void viewbluetopproduct(){
        Locator bluetop=page.locator(bluetopproductdetails);
        bluetop.click();
    }

    public String viewproductname() {
        Locator product = page.locator(blueetopproduct);
        String productname = product.textContent();
        return productname;

    }
    public String viewproductprice() {
        Locator prodprice=page.locator(blueetopproductprice);
        String productprice=prodprice.textContent();
        return productprice;
    }

    public String addbluetoptocart(){
        Locator cartbluetopbutton = page.locator(blueTopcart);
        cartbluetopbutton.click();
        Locator cartsuccessmessage=page.locator(cartmssg);
        String message=cartsuccessmessage.textContent();
        Locator viewcart=page.locator(viewbluetopcart);
        viewcart.click();
        return message;
    }

    public String addtwotbluetoptocart(int productnumber) {
        Locator productcartcount = page.locator("#quantity:visible");
        productcartcount.clear();
        productcartcount.fill(String.valueOf(productnumber));
        Locator cartbluetopbutton = page.locator(blueTopcart);
        cartbluetopbutton.click();
        Locator cartsuccessmessage=page.locator(cartmssg);
        String message=cartsuccessmessage.textContent();
        Locator viewcart=page.locator(viewbluetopcart);
        viewcart.click();
        return message;
    }

    public void addtwodifferentproductstocart() {
        Locator bluetop=page.locator(bluetopproduct);
        bluetop.click();
        Locator cartbluetopbutton = page.locator(blueTopcart);
        cartbluetopbutton.click();
        Locator cart = page.locator(cartmssg);
        String cartmessage=cart.textContent();
        System.out.println(cartmessage);
        Locator gotocart = page.getByText("View Cart", new Page.GetByTextOptions().setExact(true));
        gotocart.click();
        page.click(productlist);
        Locator menshirt=page.locator(menshirtproduct);
        menshirt.click();
        Locator menshirtbutton = page.locator(Menshirtcart);
        menshirtbutton.click();
        Locator cartmen = page.locator(cartmssg);
        String cartmenmessage=cartmen.textContent();
        System.out.println(cartmenmessage);
        Locator gotocartprod = page.getByText("View Cart", new Page.GetByTextOptions().setExact(true));
        gotocartprod.click();
    }
}


