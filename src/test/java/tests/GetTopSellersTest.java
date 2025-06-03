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
        Assert.assertTrue(steamMainPage.isLoaded(), "Steam main page isn't open");
        steamMainPage.hoverNewAndNoteworthyLink();
        TopSellersPage topSellersPage = steamMainPage.clickTopSellersLink();
        Assert.assertTrue(topSellersPage.isLoaded(), "Top sellers page isn't open");
        topSellersPage.clickCountryDropDown();
        topSellersPage.changeCountry(COUNTRY);

        String gameTitle = topSellersPage.getTitleAndPriceOfGame(NUMBER_OF_GAME).getKey();
        Double gamePrice = topSellersPage.getTitleAndPriceOfGame(NUMBER_OF_GAME).getValue();

        GamePage gamePage = topSellersPage.clickGame(NUMBER_OF_GAME);

        Assert.assertTrue(gamePage.isLoaded(), "Game page isn't open");
        Assert.assertEquals(gamePage.getGameName(), gameTitle, "Titles isn't equal");
        Assert.assertEquals(gamePage.getGamePrice(), gamePrice, "Price isn't equal");
        System.out.println(gamePage.getReleaseDate());
        System.out.println(gamePage.getDeveloperName());
        System.out.println(gamePage.getGameGenre());
    }
}
