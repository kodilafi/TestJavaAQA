package UItestforDemoblaze;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

@Owner("Дима")
public class Case1 extends SeleniumDriverOptions {
    public static int expectSum = 0;

    @Test
    @Description("UI тесты selenium/selenide")
    public void startTest () {
        //Регистрация
        SignInPage registr = new SignInPage();
        registr.SuccessfulRegistration();

        //Логин
        LogInPage login = new LogInPage();
        login.SuccessfulLogin();

        //Перестраховка
        CartPage cartPage = new CartPage();
        cartPage.DeleteCartItems();

        //Работа в каталоге
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.AddItemsBlock();

        //Работа в корзине.
        cartPage.GoToCartPageAndComparisonPrice()
                .CreateAnOrder()
                .DateComparison();
    }
}
