package UItestforDemoblaze;

import MyUtils.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Home extends BaseSeleniumPage {
    private int expectSum = 0;

    private static final String ORDER_FORM = "//div[@id='orderModal']/div/div/div[2]/form";

    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[1]/a")
    WebElement home;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Phones')]")
    WebElement phonesCategory;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Laptops')]")
    WebElement laptopsCategory;

    @FindBy(xpath = "//div[@class='list-group']/a[contains(text(), 'Monitors')]")
    WebElement monitorsCategory;

    @FindBy(xpath = "//a[contains(text(), 'Add to cart')]")
    WebElement addToCart;

    @FindBy(xpath = "//a[contains(text(), 'HTC One M9')]")
    WebElement phone;

    @FindBy(xpath = "//a[contains(text(), '2017 Dell 15.6 Inch')]")
    WebElement laptop;

    @FindBy(xpath = "//a[contains(text(), 'Apple monitor 24')]")
    WebElement monitor;

    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[4]/a")
    WebElement cart;

    @FindBy(xpath = "//button[contains(text(), 'Place Order')]")
    WebElement placeOrder;

    @FindBy(xpath = "//button[contains(text(), 'Purchase')]")
    WebElement purchase;

    public Home () {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    public Home CreateTicket () {
        //List<WebElement> item = driver.findElements(By.xpath("//div[@id='tbodyid']/div"));

        //Добавление товаров в корзину
        home.click();
        AddItem(phonesCategory, driver.findElement(By.xpath("//div[@id='tbodyid']/div[7]/div/div/h4/a")), By.xpath("//div[contains(@class, 'col-lg-4')][7]/div/div/h5"));
        home.click();
        AddItem(laptopsCategory, driver.findElement(By.xpath("//div[@id='tbodyid']/div[5]/div/div/h4/a")), By.xpath("//div[contains(@class, 'col-lg-4')][5]/div/div/h5"));
        home.click();
        AddItem(monitorsCategory, driver.findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h4/a")), By.xpath("//div[contains(@class, 'col-lg-4')][1]/div/div/h5"));
        home.click();

        //Переход к корзине в корзине
        cart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@id='totalp']")));
        int actualSum = Integer.parseInt(driver.findElement(By.xpath("//h3[@id='totalp']")).getText());
        Assertions.assertEquals(expectSum, actualSum);
        TextLog("Ожидаемая общая сумма: " + expectSum + ", Актуальная: " + actualSum + ".");

        ScreenShot("Страница с заполненной корзиной товаров.");

        placeOrder.click();

        driver.findElement(By.xpath(ORDER_FORM + "/div[1]/input")).sendKeys("Аркадий");
        driver.findElement(By.xpath(ORDER_FORM + "/div[2]/input")).sendKeys("КаунтриЛэнд");
        driver.findElement(By.xpath(ORDER_FORM + "/div[3]/input")).sendKeys("Сити-полис");
        driver.findElement(By.xpath(ORDER_FORM + "/div[4]/input")).sendKeys("0000 1234 5678 9999");
        driver.findElement(By.xpath(ORDER_FORM + "/div[5]/input")).sendKeys("Январь");
        driver.findElement(By.xpath(ORDER_FORM + "/div[6]/input")).sendKeys("2025");

        ScreenShot("Заполненная форма заказа.");

        purchase.click();

        ScreenShot("Уведомление об успешном создании заказа.");

        String[] fields = driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText().split("\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");

        TextLog("Текущая дата: " + fields[4].split(": ")[1] + ", отображаемая: " + LocalDate.now().format(formatter) + ".");
        Assertions.assertEquals(fields[4].split(": ")[1], LocalDate.now().format(formatter));

        return this;
    }

    private void AddItem (WebElement category, WebElement element, By priceXPath) {
        String priceExpect, priceActual;
        String categoryName = category.getText();

        category.click();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        priceExpect = driver.findElement(priceXPath).getText();
        element.click();
        priceActual = driver.findElement(By.xpath("//h3[@class='price-container']")).getText().split(" ")[0];

        Assertions.assertEquals(priceExpect, priceActual);
        TextLog("Цена у '" + driver.findElement(By.xpath("//h2[@class='name']")).getText()
                + "' в общем списке: " + priceExpect + ", в карточке товара: " + priceActual + ".");

        ScreenShot("Страница с '"
                + driver.findElement(By.xpath("//h2[@class='name']")).getText()
                + "' из категории '" + categoryName + "'.");

        expectSum += Integer.parseInt(priceActual.replaceAll("\\D", ""));

        addToCart.click();
    }
}
