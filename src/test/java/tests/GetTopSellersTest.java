package tests;

import models.SellersPageData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GamePage;
import pages.SteamMainPage;

public class GetTopSellersTest extends BaseTest {
    private static final String COUNTRY = "Global";
    private static final int NUMBER_OF_GAME = 1;

    @Test
    public void getTopSellers() {
        SteamMainPage steamMainPage = new SteamMainPage(driver);
//        SellersPageData sellersPageData = steamMainPage
//                .hoverNewAndNoteworthyLink()
//                .clickTopSellersLink()
//                .clickCountryDropDown()
//                .changeCountry(COUNTRY)
//                .clickGame(NUMBER_OF_GAME)
//                .saveState();
        SellersPageData sellersPageData = steamMainPage//изменить название
                .hoverNewAndNoteworthyLink()
                .clickTopSellersLink()
                .clickCountryDropDown()
                .changeCountry(COUNTRY)
                .clickGameAndSavePageData(NUMBER_OF_GAME);

        GamePage gamePage = new GamePage(driver);

        Assert.assertEquals(gamePage.getGameName(), sellersPageData.getTitle(), "Titles isn't equal");
        Assert.assertEquals(gamePage.getGamePrice(), sellersPageData.getPrice(), "Prices isn't equal");
        System.out.println(gamePage.getReleaseDate());
        System.out.println(gamePage.getDeveloperName());
        System.out.println(gamePage.getGameGenre());
    }
}
