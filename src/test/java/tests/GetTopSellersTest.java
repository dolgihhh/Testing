package tests;

import models.GameInfoData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GamePage;
import pages.SteamMainPage;

public class GetTopSellersTest extends BaseTest {
    private static final String COUNTRY = "Global";
    private static final int NUMBER_OF_GAME = 1;

    @Test
    public void getTopSellers() {
        SteamMainPage steamMainPage = new SteamMainPage();
        GameInfoData gameInfoData = steamMainPage//изменить название
                .hoverNewAndNoteworthyLink()
                .clickTopSellersLink()
                .clickCountryDropDown()
                .changeCountry(COUNTRY)
                .clickGameAndSaveGameInfoData(NUMBER_OF_GAME);//не принимать номер игры, рандомно из существующих

        GamePage gamePage = new GamePage();

        Assert.assertEquals(gamePage.getGameName(), gameInfoData.getTitle(), "Titles isn't equal");
        Assert.assertEquals(gamePage.getGamePrice(), gameInfoData.getPrice(), "Prices isn't equal");
    }
}
