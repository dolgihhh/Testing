package pages;

import models.SellersPageData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.NumbersUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TopSellersPage extends BasePage {
    //private SellersPageData sellersPageData;

    @FindBy(xpath = "//button[contains(@class, 'DialogDropDown')]")
    private WebElement countryDropDown;

    @FindBy(xpath = "//div[text()='Top 100 selling games right now, by revenue']/following-sibling::div[1]//tbody//tr")
    private List<WebElement> gamesElements;


    public TopSellersPage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(this.isLoaded(), "Top sellers page isn't loaded");
    }

    public TopSellersPage clickCountryDropDown() {
        click(countryDropDown);

        return this;
    }

    public TopSellersPage changeCountry(String country) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div" +
                "[contains(@class, 'DialogInputContainer')]//div[text()='%s']", country))));
        click(element);

        return this;
    }

//    public SellersPageData saveState() {
//
//        return sellersPageData;
//    }

//    public GamePage clickGame(int number) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(gamesElements));
//        if (number < 1 || number > gamesElements.size()) {
//            throw new IllegalArgumentException("Invalid game number: " + number);
//        }
//        wait.until(ExpectedConditions.visibilityOf(gamesElements.get(number - 1)));
//        var gameTitleAndPrice = getTitleAndPriceOfGame(number);
//        //gameTitle = gameTitleAndPrice.getKey();
//        //gamePrice = gameTitleAndPrice.getValue();
//        sellersPageData = new SellersPageData();
//        sellersPageData.setTitle(gameTitleAndPrice.getKey());
//        sellersPageData.setPrice(gameTitleAndPrice.getValue());
//
//        click(gamesElements.get(number - 1));
//
//        return new GamePage(driver);
//    }

    public SellersPageData clickGameAndSavePageData(int number) {
        wait.until(ExpectedConditions.visibilityOfAllElements(gamesElements));
        if (number < 1 || number > gamesElements.size()) {
            throw new IllegalArgumentException("Invalid game number: " + number);
        }
        wait.until(ExpectedConditions.visibilityOf(gamesElements.get(number - 1)));
        var gameTitleAndPrice = getTitleAndPriceOfGame(number);
        //SellersPageData sellersPageData = new SellersPageData();
        //sellersPageData.setTitle(gameTitleAndPrice.getKey());
        //sellersPageData.setPrice(gameTitleAndPrice.getValue());
        SellersPageData sellersPageData = SellersPageData.builder()
                .title(gameTitleAndPrice.getKey())
                .price(gameTitleAndPrice.getValue())
                .build();

        click(gamesElements.get(number - 1));

        return sellersPageData;
    }

    private Map<String, Double> getTitlesAndPrices() {
        Map<String, Double> titleAndPriceMap = new LinkedHashMap<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(gamesElements));
        List<WebElement> topTenGames = gamesElements.subList(0, 10);
        for (WebElement gameElement: topTenGames) {
            String name = gameElement.findElement(By.xpath("./td[3]/a/div")).getText();
            WebElement priceElement = gameElement.findElement(By.xpath("./td[4]"));
            while (true) {
                List<WebElement> childDivs = priceElement.findElements(By.xpath("./div"));
                if (childDivs.isEmpty()) {
                    break;
                }
                priceElement = childDivs.get(childDivs.size() - 1); // идём в последний <div>
            }
            Double price = NumbersUtils.strToDouble(priceElement.getText());
            titleAndPriceMap.put(name, price);
        }

        return titleAndPriceMap;
    }

    private Map.Entry<String, Double> getTitleAndPriceOfGame(int gameNumber) {
        Map<String, Double> titlesAndPrices = getTitlesAndPrices();

        if (gameNumber < 1 || gameNumber >= titlesAndPrices.size()) {
            throw new IndexOutOfBoundsException("Invalid game number: " + gameNumber);
        }

        return titlesAndPrices.entrySet()
                .stream()
                .skip(gameNumber - 1)
                .findFirst()
                .orElseThrow();
    }

//    public String getGameTitle() {
//        return gameTitle;
//    }

//    public Double getGamePrice() {
//        return gamePrice;
//    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(countryDropDown);
    }
}
