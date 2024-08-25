package UItestforDemoblaze;

import MyUtils.BaseSeleniumPage;
import MyUtils.MyUtils;
import org.openqa.selenium.support.PageFactory;

public class Case1 extends BaseSeleniumPage {
    public static int expectSum = 0;

    public Case1() {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    public Case1 startTest () {
        //Регистрация
        RegistrationPage registr = new RegistrationPage(driver);
        registr.SuccessfulRegistration();

        //Логин
        LogInPage login = new LogInPage(driver);
        login.SuccessfulLogin();

        //Работа в каталоге
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.CreateTicket();

        //Работа в корзине.
        CartPage cartPage = new CartPage(driver);
        cartPage.GoToCartPageAndComparisonPrice();
        cartPage.CreateAnOrder();
        cartPage.DateComparison();

        return this;
    }
}
