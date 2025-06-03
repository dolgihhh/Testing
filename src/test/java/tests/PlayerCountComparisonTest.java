package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SteamAboutPage;
import pages.SteamMainPage;

public class PlayerCountComparisonTest extends BaseTest {
    @Test
    public void comparePlayers() {
        SteamMainPage steamMainPage = new SteamMainPage(driver);
        Assert.assertTrue(steamMainPage.isLoaded(), "Main Page isn't open");
        SteamAboutPage steamAboutPage = steamMainPage.clickAboutLink();
        Assert.assertTrue(steamAboutPage.isLoaded(), "About Page isn't open");
        Assert.assertTrue(steamAboutPage.getPlayersOnline().compareTo(steamAboutPage.getPlayersInGame()) > 0,
                "There are more players in game then online");
    }
}
