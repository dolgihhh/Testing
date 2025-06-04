package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class SteamMainPage extends BasePage {
    private static final String URL = "https://store.steampowered.com/";

    @FindBy(xpath = "//a[contains(@class, 'menuitem') and contains(text(), 'About') and ../@role='navigation']")
    private WebElement aboutLink;

    @FindBy(xpath = "//a[@class='pulldown_desktop' and text()='New & Noteworthy']")
    private WebElement newAndNoteworthyLink;

    @FindBy(xpath = "//div[contains(@class, 'popup_menu_subheader') and contains(text(), 'Popular')]/../..")
    private WebElement noteworthySubmenu;

    @FindBy(xpath = "//a[@class='popup_menu_item' and contains(text(), 'Top Sellers')]")
    private WebElement topSellersLink;

    public SteamMainPage(WebDriver driver) {
        super(driver);
        driver.get(URL);
        Assert.assertTrue(this.isLoaded(), "Main page isn't loaded");
    }

    public SteamAboutPage clickAboutLink() {
        click(aboutLink);

        return new SteamAboutPage(driver);
    }

    public SteamMainPage hoverNewAndNoteworthyLink() {
        hover(newAndNoteworthyLink, noteworthySubmenu);

        return this;
    }

    public TopSellersPage clickTopSellersLink() {
        click(topSellersLink);

        return new TopSellersPage(driver);
    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(aboutLink);
    }
}
