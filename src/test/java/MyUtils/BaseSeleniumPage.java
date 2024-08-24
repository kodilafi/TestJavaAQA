package MyUtils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BaseSeleniumPage {
    protected static Actions actions;
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setDriver (WebDriver webDriver) {
        driver = webDriver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Создание скриншота")
    @Attachment(value = "{0}", type = "image/png")
    public static byte[] ScreenShot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @Step("Создание скриншота")
    @Attachment(value = "Скриншот", type = "image/png")
    public static byte[] ScreenShot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @Attachment(value = "Информация:", type = "text/plain")
    public static String TextLog(String message) {
        return message;
    }
}
