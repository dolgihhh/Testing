package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsMainPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public SwagLabsMainPage(WebDriver driver) {
        super(driver);
        driver.get(URL);
    }

    public void fillUsername(String text){
        sendKeys(usernameInput, text);
    }

    public void fillPassword(String text) {
        sendKeys(passwordInput, text);
    }

    public SwagLabsProductsPage clickLoginBtn() {
        click(loginBtn);

        return new SwagLabsProductsPage(driver);
    }



    @Override
    public boolean isLoaded() {
        return isElementDisplayed(usernameInput);
    }
}
