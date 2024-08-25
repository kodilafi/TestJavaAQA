package UItestforDemoblaze;

import MyUtils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BaseSeleniumPage {
    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li[8]/a")
    private WebElement signUp_OpenForm;

    @FindBy(xpath = "//input[contains(@id, 'sign-username')]")
    private WebElement userName;

    @FindBy(xpath = "//input[contains(@id, 'sign-password')]")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Sign up')]")
    private WebElement signUp_SubmitButton;

    @FindBy(xpath = "//div[@id='signInModal']/div/div/div/button")
    private WebElement closeButton;

    public RegistrationPage() {
        driver.get(MyUtils.URL_DEMOBLASE);
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Регистрация.")
    public void SuccessfulRegistration() {
        //Открытие окна с регистрацией
        signUp_OpenForm.click();

        //Ввод данных
        wait.until(ExpectedConditions.elementToBeClickable(userName));
        userName.sendKeys(MyUtils.USERNAME);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys(MyUtils.PASSWORD);

        //Подтверждение данных.
        actions.click(signUp_SubmitButton);

        //На случай если уже есть такой пользователь.
        try {
            closeButton.click();
        } catch (ElementClickInterceptedException e) {

        }

        //Снимок
        ScreenShot("Скриншот с заполненными полями из формы регистрации.");
    }
}
