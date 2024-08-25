package UItestforDemoblaze;

import MyUtils.*;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Owner("Дима")
public class CartPage extends BaseSeleniumPage {
    private static final String ORDER_FORM = "//div[@id='orderModal']/div/div/div[2]/form";

    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[4]/a")
    private WebElement cart;

    @FindBy(xpath = "//button[contains(text(), 'Place Order')]")
    private WebElement placeOrder;

    @FindBy(xpath = "//button[contains(text(), 'Purchase')]")
    private WebElement purchase;

    @FindBy(xpath = "//a[text()='Delete']")
    private List<WebElement> delete;

    public CartPage() {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    @Step("Переходим в корзину и убедеждаемся, что общая цена посчитана верно.")
    public CartPage GoToCartPageAndComparisonPrice() {
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

        return this;
    }

    @Step("Оформляем заказ.")
    public CartPage CreateAnOrder() {
        //Нажимаем кнопку PlaceOrder.
        placeOrder.click();

        //Вводим данные в форму оформления заказа.
        driver.findElement(By.xpath(ORDER_FORM + "/div[1]/input")).sendKeys("Аркадий");
        driver.findElement(By.xpath(ORDER_FORM + "/div[2]/input")).sendKeys("Странная");
        driver.findElement(By.xpath(ORDER_FORM + "/div[3]/input")).sendKeys("Городской");
        driver.findElement(By.xpath(ORDER_FORM + "/div[4]/input")).sendKeys("0000 1234 5678 9999");
        driver.findElement(By.xpath(ORDER_FORM + "/div[5]/input")).sendKeys("Май");
        driver.findElement(By.xpath(ORDER_FORM + "/div[6]/input")).sendKeys("2025");

        //Очередное фото на память.
        ScreenShot("Заполненная форма заказа.");

        //Подтверждаем заказ
        purchase.click();

        //Память на фото.
        ScreenShot("Уведомление об успешном создании заказа.");

        return this;
    }

    @Step("Убедждаемся, что дата в конце заказа совпадает с датой в системе.")
    public CartPage DateComparison() {
        //Выбираем поле с данными о заказе, и достаём их разбивая на отдельные строки.
        String[] fields = driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText().split("\n");

        //Создаём формат даты для более корректного сравнения.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");

        //Сравнение дат. Фактической и той что выводит сервис.
        TextLog("Текущая дата: " + LocalDate.now().format(formatter) + ", отображаемая: " + fields[4].split(": ")[1] + ".");
        Assertions.assertEquals(fields[4].split(": ")[1], LocalDate.now().format(formatter));

        return this;
    }

    @Step("На всякий случай проверить корзину и очистить её если, есть лишний хлам.")
    public CartPage DeleteCartItems() {
        //Переходим в корзину.
        cart.click();

        //Если есть товары, удаляем.
        if (delete.size() > 0) {
            for (int i = delete.size() - 1; i >= 0; i--) {
                delete.get(i).click();
            }
        }

        return this;
    }
}
