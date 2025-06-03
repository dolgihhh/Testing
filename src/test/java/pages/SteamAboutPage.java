package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.NumbersUtils;


public class SteamAboutPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class,'gamers_online')]/parent::div")
    private WebElement playersOnlineDiv;

    @FindBy(xpath = "//div[contains(@class,'gamers_in_game')]/parent::div")
    private WebElement playersInGameDiv;

    public SteamAboutPage(WebDriver driver) {
        super(driver);
    }

    public Integer getPlayersInGame() {
        return NumbersUtils.strToInt(getText(playersInGameDiv));
    }

    public Integer getPlayersOnline() {
        return NumbersUtils.strToInt(getText(playersOnlineDiv));
    }



    @Override
    public boolean isLoaded() {
        return isElementDisplayed(playersOnlineDiv);
    }
}
