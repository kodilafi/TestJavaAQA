package UItestforDemoblaze;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

@Owner("Дима")
public class StartTest extends SeleniumDriverOptions {
    @Test
    @Description("UI тесты selenium/selenide")
    public void AddToCart () {
        Case1 case1 = new Case1();
        case1.startTest();
    }
}
