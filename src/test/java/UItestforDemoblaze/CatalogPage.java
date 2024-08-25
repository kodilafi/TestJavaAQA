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

@Owner("Дима")
public class CatalogPage extends BaseSeleniumPage {
    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[1]/a")
    private WebElement home;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Phones')]")
    private WebElement phonesCategory;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Laptops')]")
    private WebElement laptopsCategory;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Monitors')]")
    private WebElement monitorsCategory;

    @FindBy(xpath = "//a[contains(text(), 'Add to cart')]")
    private WebElement addToCart;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void CreateTicket () {
        //Добавление товаров в корзину
        home.click();
        AddItem(phonesCategory);
        home.click();
        AddItem(laptopsCategory);
        home.click();
        AddItem(monitorsCategory);
        home.click();
    }

    @Step("Добавляем в корзину по одному 'случайному' гаджету из каждой категории.")
    private void AddItem (WebElement category) {
        String priceExpect, priceActual;
        String categoryName = category.getText();
        Faker faker = new Faker();

        //Переходим в нужную категорию.
        category.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Ждём загрузки нужных элементов.
        int random = faker.random().nextInt(1, driver.findElements(By.xpath("//div[@id='tbodyid']/div")).size());
        WebElement item = driver.findElement(By.xpath("//div[@id='tbodyid']/div[" + random + "]/div/div/h4/a"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']/div[" + random + "]/div/div/h5")));
        priceExpect = driver.findElement(By.xpath("//div[@id='tbodyid']/div[" + random + "]/div/div/h5")).getText();

        //Переходим в карточку товара.
        wait.until(ExpectedConditions.elementToBeClickable(item));
        item.click();

        priceActual = driver.findElement(By.xpath("//h3[@class='price-container']")).getText().split(" ")[0];
        Assertions.assertEquals(priceExpect, priceActual);
        TextLog("Цена у '" + driver.findElement(By.xpath("//h2[@class='name']")).getText()
                + "' в общем списке: " + priceExpect + ", в карточке товара: " + priceActual + ".");

        ScreenShot("Страница с '"
                + driver.findElement(By.xpath("//h2[@class='name']")).getText()
                + "' из категории '" + categoryName + "'.");

        Case1.expectSum += Integer.parseInt(priceActual.replaceAll("\\D", ""));

        addToCart.click();
    }

    @Step("Сравниваем цену из общего списка и с карточки товара.")
    public void ComparisonItemPrice() {

    }
}
