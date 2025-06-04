package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.NumbersUtils;

public class GamePage extends BasePage {
    @FindBy(id = "appHubAppName")
    private WebElement gameName;

    @FindBy(xpath = "//div[contains(@class, 'game_purchase_price price')]")
    private WebElement gamePrice;

    @FindBy(className = "date")
    private WebElement releaseDate;

    @FindBy(xpath = "//div[@id='developers_list']/a")
    private WebElement developer;

    @FindBy(xpath = "//b[contains(text(), 'Genre')]//following-sibling::span/a[1]")
    private WebElement genre;


    public GamePage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(this.isLoaded(), "Game page isn't loaded");
    }

    public String getGameName() {
        return getText(gameName);
    }

    public String getReleaseDate() {
        scrollTo(developer);
        return getText(releaseDate);
    }

    public String getDeveloperName() {
        scrollTo(developer);
        return getText(developer);
    }

    public String getGameGenre() {
        scrollTo(genre);
        return getText(genre);
    }


    public Double getGamePrice() {
        return NumbersUtils.strToDouble(getText(gamePrice));
    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(gameName);
    }
}
