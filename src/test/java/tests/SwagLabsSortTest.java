package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SwagLabsMainPage;
import pages.SwagLabsProductsPage;

public class SwagLabsSortTest extends BaseTest {
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private static final String SORT_VALUE = "lohi";

    @Test
    public void sortTest() throws InterruptedException {
        SwagLabsMainPage swagLabsMainPage = new SwagLabsMainPage(driver);
        swagLabsMainPage.fillUsername(USERNAME);
        swagLabsMainPage.fillPassword(PASSWORD);
        SwagLabsProductsPage swagLabsProductsPage = swagLabsMainPage.clickLoginBtn();
        swagLabsProductsPage.selectSortByValue(SORT_VALUE);
        Assert.assertTrue(swagLabsProductsPage.isSortedBy(SORT_VALUE),
                "Products aren't sorted in right way");
    }
}
