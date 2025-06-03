package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.NumbersUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TopSellersPage extends BasePage {

    //@FindBy(xpath = "//div[contains(@class, 'DialogInputContainer')]//div[text()='Global']")
    //private WebElement globalButton;

    @FindBy(xpath = "//button[contains(@class, 'DialogDropDown')]")
    private WebElement countryDropDown;

    @FindBy(xpath = "//div[text()='Top 100 selling games right now, by revenue']/following-sibling::div[1]//tbody//tr")
    private List<WebElement> gamesElements;


    public TopSellersPage(WebDriver driver) {
        super(driver);
    }

    public void clickCountryDropDown() {
        click(countryDropDown);
    }

    public void changeCountry(String country) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div" +
                "[contains(@class, 'DialogInputContainer')]//div[text()='%s']", country))));
        click(element);
    }

    public GamePage clickGame(int number) {
        if (number < 1 || number > gamesElements.size()) {
            throw new IllegalArgumentException("Invalid game number: " + number);
        }
        wait.until(ExpectedConditions.visibilityOf(gamesElements.get(number - 1)));
        click(gamesElements.get(number - 1));

        return new GamePage(driver);
    }

    public Map<String, Double> getTitlesAndPrices() {
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

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(countryDropDown);
    }
}
