package UItestforDemoblaze;

import MyUtils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BaseSeleniumPage {
    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[5]/a")
    private WebElement logIn_OpenForm;

    @FindBy(xpath = "//input[contains(@id, 'loginusername')]")
    private WebElement userName;

    @FindBy(xpath = "//input[contains(@id, 'loginpassword')]")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Log in')]")
    private WebElement logIn_SubmitButton;

    @FindBy(xpath = "//a[@id='nameofuser']")
    WebElement nameOfUser;

    public LogInPage() {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    @Step("Логин.")
    public LogInPage SuccessfulLogin() {
        //Логин на сайте.
        wait.until(ExpectedConditions.elementToBeClickable(logIn_OpenForm));
        logIn_OpenForm.click();

        //Ввод данных в поля UserName и Password
        wait.until(ExpectedConditions.elementToBeClickable(userName));
        userName.sendKeys(MyUtils.USERNAME);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(MyUtils.PASSWORD);

        //Подтверждение данных
        logIn_SubmitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(nameOfUser));
        ScreenShot("Скриншот с результатом того, как выглядит окно главного меню, после успешного логина.");

        return this;
    }
}
