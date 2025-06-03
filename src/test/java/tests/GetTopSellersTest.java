package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GamePage;
import pages.SteamMainPage;
import pages.TopSellersPage;

import java.util.Map;

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

        Map<String, Double> titlesAndPrices = topSellersPage.getTitlesAndPrices();
        var firstEntry = titlesAndPrices.entrySet().iterator().next();
        String firstTitle = firstEntry.getKey();
        Double firstPrice = firstEntry.getValue();

        GamePage gamePage = topSellersPage.clickGame(NUMBER_OF_GAME);
        Assert.assertTrue(gamePage.isLoaded(), "Game page isn't open");
        Assert.assertEquals(firstTitle, gamePage.getGameName(), "Titles isn't equal");
        Assert.assertEquals(firstPrice, gamePage.getGamePrice(), "Price isn't equal");
        System.out.println(gamePage.getReleaseDate());
        System.out.println(gamePage.getDeveloperName());
        System.out.println(gamePage.getGameGenre());
    }
}
