package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static final int MAX_WAIT = 10;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isLoaded();

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected void hover(WebElement elementToHover, WebElement elementToBecomeVisible) {
        wait.until(ExpectedConditions.visibilityOf(elementToHover));
        new Actions(driver).moveToElement(elementToHover).perform();
        wait.until(ExpectedConditions.visibilityOf(elementToBecomeVisible));
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
