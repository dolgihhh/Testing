package tests;

import models.SortValue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SwagLabsMainPage;
import pages.SwagLabsProductsPage;
import utils.ConfigReader;

import java.util.Arrays;

public class SwagLabsSortTest extends BaseTest {
    private static final String USERNAME = ConfigReader.get("username");
    private static final String PASSWORD = ConfigReader.get("password");
    //private static final SortValue SORT_VALUE = SortValue.getSortValueFromConfig();

    @DataProvider(name = "sortValues", parallel = true)
    public Object[][] allSortValues() {
        return Arrays.stream(SortValue.values())
                .map(v -> new Object[] {v})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "sortValues")
    public void sortTest(SortValue sortValue) {
        SwagLabsMainPage swagLabsMainPage = new SwagLabsMainPage();
        SwagLabsProductsPage swagLabsProductsPage = swagLabsMainPage
                .fillUsername(USERNAME)
                .fillPassword(PASSWORD)
                .clickLoginBtn()
                .selectSortByValue(sortValue.getValue());

        Assert.assertTrue(swagLabsProductsPage.isSorted(sortValue), "Products aren't sorted in "
                + sortValue);
    }

    @Test
    public void unsuccessfulLogin() {
        SwagLabsMainPage swagLabsMainPage = new SwagLabsMainPage();
        swagLabsMainPage.fillUsername(USERNAME)
                .fillPassword("Wrong password")
                .clickLoginBtn();

        Assert.assertTrue(swagLabsMainPage.isLoginErrorDisplayed(),
                "Expected error message is not displayed");
    }


}
