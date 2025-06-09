package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DriverManager;

public class SwagLabsMainPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(css = "*[data-test='error']")
    private WebElement errorMessage;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public SwagLabsMainPage() {
        super(DriverManager.getDriver());
        driver.get(URL);
    }

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public SwagLabsMainPage fillUsername(String text){
        sendKeys(usernameInput, text);

        return this;
    }

    public SwagLabsMainPage fillPassword(String text) {
        sendKeys(passwordInput, text);

        return this;
    }

    public SwagLabsProductsPage clickLoginBtn() {
        click(loginBtn);

        return new SwagLabsProductsPage();
    }



    @Override
    public boolean isLoaded() {
        return isElementDisplayed(usernameInput);
    }
}
