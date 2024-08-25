package UItestforDemoblaze;

import MyUtils.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Owner("Дима")
public class CartPage extends BaseSeleniumPage {
    private static final String ORDER_FORM = "//div[@id='orderModal']/div/div/div[2]/form";

    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[4]/a")
    private WebElement cart;

    @FindBy(xpath = "//button[contains(text(), 'Place Order')]")
    private WebElement placeOrder;

    @FindBy(xpath = "//button[contains(text(), 'Purchase')]")
    private WebElement purchase;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Переходим в корзину и убедеждаемся, что общая цена посчитана верно.")
    public void GoToCartPageAndComparisonPrice() {
        //Переходим в корзину.
        cart.click();

        //Ждём загрузки, нужного для сравнения, элемента (итоговой суммы заказа).
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@id='totalp']")));
        int actualSum = Integer.parseInt(driver.findElement(By.xpath("//h3[@id='totalp']")).getText());

        //Сравниваем итоговую сумму заказа.
        Assertions.assertEquals(Case1.expectSum, actualSum);
        TextLog("Ожидаемая общая сумма: " + Case1.expectSum + ", Актуальная: " + actualSum + ".");

        //Фото на память.
        ScreenShot("Страница с заполненной корзиной товаров.");
    }

    @Step("Оформляем заказ.")
    public void CreateAnOrder() {
        //Нажимаем кнопку PlaceOrder.
        placeOrder.click();

        //Вводим данные в форму оформления заказа.
        Faker faker = new Faker();
        driver.findElement(By.xpath(ORDER_FORM + "/div[1]/input")).sendKeys(faker.name().name());
        driver.findElement(By.xpath(ORDER_FORM + "/div[2]/input")).sendKeys(faker.country().name());
        driver.findElement(By.xpath(ORDER_FORM + "/div[3]/input")).sendKeys(faker.address().city());
        driver.findElement(By.xpath(ORDER_FORM + "/div[4]/input")).sendKeys(faker.number().digits(16));
        driver.findElement(By.xpath(ORDER_FORM + "/div[5]/input")).sendKeys("Май");
        driver.findElement(By.xpath(ORDER_FORM + "/div[6]/input")).sendKeys("2025");

        //Очередное фото на память.
        ScreenShot("Заполненная форма заказа.");

        //Подтверждаем заказ
        purchase.click();

        //Память на фото.
        ScreenShot("Уведомление об успешном создании заказа.");
    }

    @Step("Убедждаемся, что дата в конце заказа совпадает с датой в системе.")
    public void DateComparison() {
        //Выбираем поле с данными о заказе, и достаём их разбивая на отдельные строки.
        String[] fields = driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText().split("\n");

        //Создаём формат даты для более корректного сравнения.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");

        //Сравнение дат. Фактической и той что выводит сервис.
        TextLog("Текущая дата: " + fields[4].split(": ")[1] + ", отображаемая: " + LocalDate.now().format(formatter) + ".");
        Assertions.assertEquals(fields[4].split(": ")[1], LocalDate.now().format(formatter));
    }
}
