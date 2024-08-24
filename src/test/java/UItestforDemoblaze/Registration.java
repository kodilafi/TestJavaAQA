package UItestforDemoblaze;

import MyUtils.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Registration extends BaseSeleniumPage {
    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[8]/a")
    WebElement signUp_OpenForm;

    @FindBy(xpath = "//input[contains(@id, 'sign-username')]")
    WebElement userName;

    @FindBy(xpath = "//input[contains(@id, 'sign-password')]")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Sign up')]")
    WebElement signUp_SubmitButton;

    public Registration () {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    public Registration createTicket() {
        signUp_OpenForm.click();
        wait.until(ExpectedConditions.elementToBeClickable(userName));
        userName.sendKeys(MyUtils.USERNAME);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(MyUtils.PASSWORD);
        signUp_SubmitButton.click();

        ScreenShot("Скриншот с заполненными полями из формы регистрации.");

        return this;
    }
}
