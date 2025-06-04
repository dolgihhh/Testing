package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GamePage;
import pages.SteamMainPage;
import pages.TopSellersPage;

public class GetTopSellersTest extends BaseTest {
    private static final String COUNTRY = "Global";
    private static final int NUMBER_OF_GAME = 1;

    @Test
    public void getTopSellers() {
        SteamMainPage steamMainPage = new SteamMainPage(driver);
        TopSellersPage topSellersPage = steamMainPage.hoverNewAndNoteworthyLink()
                .clickTopSellersLink()
                .clickCountryDropDown()
                .changeCountry(COUNTRY);

        GamePage gamePage = topSellersPage.clickGame(NUMBER_OF_GAME);
        Assert.assertEquals(gamePage.getGameName(), topSellersPage.getGameTitle(), "Titles isn't equal");
        Assert.assertEquals(gamePage.getGamePrice(), topSellersPage.getGamePrice(), "Price isn't equal");
        System.out.println(gamePage.getReleaseDate());
        System.out.println(gamePage.getDeveloperName());
        System.out.println(gamePage.getGameGenre());
    }
}
