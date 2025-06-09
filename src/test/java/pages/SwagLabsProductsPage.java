package pages;

import models.SortValue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.DriverManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SwagLabsProductsPage extends BasePage {

    @FindBy(className = "product_sort_container")
    private WebElement sortSelect;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemsNames;

    public SwagLabsProductsPage() {
        super(DriverManager.getDriver());
    }

    public SwagLabsProductsPage selectSortByValue(String value) {
        wait.until(ExpectedConditions.visibilityOf(sortSelect));
        Select select = new Select(sortSelect);
        select.selectByValue(value);

        return this;
    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(sortSelect);
    }

    public boolean isSorted(SortValue sortValue) {
        return switch (sortValue) {
            case PRICE_LOW_TO_HIGH -> isPriceSorted(true);
            case PRICE_HIGH_TO_LOW -> isPriceSorted(false);
            case NAME_A_TO_Z -> isNameSorted(true);
            case NAME_Z_TO_A -> isNameSorted(false);
            default -> throw new IllegalArgumentException("Unsupported sort value: " + sortValue);
        };
    }

    private boolean isPriceSorted(boolean ascending) {
        List<Double> prices = itemsPrices.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("[^\\d.]", ""))
                .filter(s -> !s.isEmpty())
                .map(Double::valueOf)
                .toList();

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return prices.equals(sorted);
    }

    private boolean isNameSorted(boolean ascending) {
        List<String> names = itemsNames.stream()
                .map(WebElement::getText)
                .filter(s -> !s.isBlank())
                .toList();

        List<String> sorted = new ArrayList<>(names);
        sorted.sort(ascending ? String.CASE_INSENSITIVE_ORDER : String.CASE_INSENSITIVE_ORDER.reversed());

        return names.equals(sorted);
    }
}
