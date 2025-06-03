package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwagLabsProductsPage extends BasePage {

    @FindBy(className = "product_sort_container")
    private WebElement sortSelect;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    public SwagLabsProductsPage(WebDriver driver) {
        super(driver);
    }

    public void selectSortByValue(String value) {
        wait.until(ExpectedConditions.visibilityOf(sortSelect));
        Select select = new Select(sortSelect);
        select.selectByValue(value);
    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(sortSelect);
    }

    public boolean isSortedBy(String sortValue) {
        switch (sortValue) {
            case "lohi" :
                List<Double> itemPrices = itemsPrices.stream()
                    .map(WebElement::getText)
                    .map(s -> s.replaceAll("[^\\d.]", ""))
                    .map(Double::valueOf)
                    .toList();
                List<Double> compareList = new ArrayList<>(itemPrices);
                Collections.sort(compareList);
                return compareList.equals(itemPrices);
            default: return false;
        }

    }
}
