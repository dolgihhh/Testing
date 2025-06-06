package tests;

import models.SortValue;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SwagLabsMainPage;
import pages.SwagLabsProductsPage;
import utils.ConfigReader;

public class SwagLabsSortTest extends BaseTest {
    private static final String USERNAME = ConfigReader.get("username");//хранить в .properties
    private static final String PASSWORD = ConfigReader.get("password");
    private static final SortValue SORT_VALUE = SortValue.getSortValueFromConfig();//

    @Test
    public void sortTest() {
        SwagLabsMainPage swagLabsMainPage = new SwagLabsMainPage(driver);
        swagLabsMainPage.fillUsername(USERNAME);
        swagLabsMainPage.fillPassword(PASSWORD);
        SwagLabsProductsPage swagLabsProductsPage = swagLabsMainPage.clickLoginBtn();
        swagLabsProductsPage.selectSortByValue(SORT_VALUE.getValue());
        Assert.assertTrue(swagLabsProductsPage.isSortedByPrice(SORT_VALUE), // итерироваться по ENUM
                "Products aren't sorted in right way");
    }
}
