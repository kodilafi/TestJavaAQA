package UItestforDemoblaze;

import MyUtils.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogIn extends BaseSeleniumPage {
    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[5]/a")
    WebElement logIn_OpenForm;

    @FindBy(xpath = "//input[contains(@id, 'loginusername')]")
    WebElement userName;

    @FindBy(xpath = "//input[contains(@id, 'loginpassword')]")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Log in')]")
    WebElement logIn_SubmitButton;

    @FindBy(xpath = "//a[@id='nameofuser']")
    WebElement nameOfUser;



    public LogIn () {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    public LogIn createTicket() {
        logIn_OpenForm.click();
        wait.until(ExpectedConditions.elementToBeClickable(userName));
        userName.sendKeys(MyUtils.USERNAME);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(MyUtils.PASSWORD);
        logIn_SubmitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(nameOfUser));
        ScreenShot("Скриншот с результатом того, как выглядит окно главного меню, после успешного логина.");

        return this;
    }
}
