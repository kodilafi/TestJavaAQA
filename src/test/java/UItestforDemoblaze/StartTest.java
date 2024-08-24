package UItestforDemoblaze;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

@Owner("Дима")
public class StartTest extends SeleniumDriverOptions {
    @Test
    @Owner("Регистрация")
    public void RegistrationOnSite () {
        Registration registr = new Registration();
        registr.createTicket();
    }

    @Test
    @Owner("Логин")
    public void LoginOnSite () {
        LogIn login = new LogIn();
        login.createTicket();
    }

    @Test
    @Owner("Добавление товаров в корзину")
    public void AddToCart () {
        Home cartOperations = new Home();
        cartOperations.CreateTicket();
    }
}
