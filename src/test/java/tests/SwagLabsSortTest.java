package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SwagLabsMainPage;
import pages.SwagLabsProductsPage;
import utils.ConfigReader;

public class SwagLabsSortTest extends BaseTest {
    private static final String USERNAME = ConfigReader.get("username");//хранить в .properties
    private static final String PASSWORD = ConfigReader.get("password");
    private static final String SORT_VALUE = ConfigReader.get("sort_value");

    @Test
    public void sortTest() {
        SwagLabsMainPage swagLabsMainPage = new SwagLabsMainPage(driver);
        swagLabsMainPage.fillUsername(USERNAME);
        swagLabsMainPage.fillPassword(PASSWORD);
        SwagLabsProductsPage swagLabsProductsPage = swagLabsMainPage.clickLoginBtn();
        swagLabsProductsPage.selectSortByValue(SORT_VALUE);
        Assert.assertTrue(swagLabsProductsPage.isSortedBy(SORT_VALUE),
                "Products aren't sorted in right way");
    }
}
