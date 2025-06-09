package pages;

import models.DemoQaFormData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.DateUtils;
import utils.DriverManager;
import utils.NumbersUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class DemoQaFormPage extends BasePage {
    private static final String URL = "https://demoqa.com/automation-practice-form";

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement userEmailInput;

    @FindBy(xpath = "//*[@name='gender']")
    private List<WebElement> gendersCheckboxes;

    @FindBy(xpath = "//*[contains(@for, 'gender-radio')]")
    private List<WebElement> genderLabels;

    @FindBy(id = "userNumber")
    private WebElement userNumberInput;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(className = "react-datepicker__month-select")
    private WebElement monthSelect;

    @FindBy(className = "react-datepicker__year-select")
    private WebElement yearSelect;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;

    @FindBy(xpath = "//*[@type='checkbox']")
    private List<WebElement> hobbiesComboboxes;

    @FindBy(xpath = "//*[contains(@for, 'hobbies-checkbox')]")
    private List<WebElement> hobbiesLabels;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPicture;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(xpath = "//*[@id='state']/..")
    private WebElement stateDiv;

    @FindBy(id = "react-select-3-input")
    private WebElement stateInput;

    @FindBy(xpath = "//div[contains(@id, 'react-select-3-option')]")
    private List<WebElement> stateInputOptions;

    @FindBy(xpath = "//*[@id='city']/..")
    private WebElement cityDiv;

    @FindBy(xpath = "//div[contains(@id, 'react-select-4-option')]")
    private List<WebElement> cityInputOptions;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//td[text()='Student Name']/following-sibling::td[1]")
    private WebElement studentName;

    @FindBy(xpath = "//td[text()='Student Email']/following-sibling::td[1]")
    private WebElement studentEmail;

    @FindBy(xpath = "//td[text()='Gender']/following-sibling::td[1]")
    private WebElement gender;

    @FindBy(xpath = "//td[text()='Mobile']/following-sibling::td[1]")
    private WebElement mobile;

    @FindBy(xpath = "//td[text()='Date of Birth']/following-sibling::td[1]")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//td[text()='Hobbies']/following-sibling::td[1]")
    private WebElement hobbies;

    @FindBy(xpath = "//td[text()='State and City']/following-sibling::td[1]")
    private WebElement stateAndCity;

    @FindBy(xpath = "//td[text()='Address']/following-sibling::td[1]")
    private WebElement address;

    @FindBy(xpath = "//td[text()='Picture']/following-sibling::td[1]")
    private WebElement picture;

    @FindBy(xpath = "//td[text()='Subjects']/following-sibling::td[1]")
    private WebElement subjects;

    public DemoQaFormPage() {
        super(DriverManager.getDriver());
        driver.get(URL);
        Assert.assertTrue(this.isLoaded(), "Demo QA form page isn't loaded");
    }

    private void fillFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    private void fillLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    private void fillUserEmail(String userEmail) {
        sendKeys(userEmailInput, userEmail);
    }

    private void chooseGenderByValue(String value) {
        for (WebElement genderCheckbox: gendersCheckboxes) {
            if (Objects.equals(genderCheckbox.getAttribute("value"), value)) {
                String id = genderCheckbox.getAttribute("id");
                WebElement labelConnectedToCheckbox =
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[@for='%s']",
                                id))));
                labelConnectedToCheckbox.click();
            }
        }
    }

    private void fillUserPhoneNumber(String number) {
        sendKeys(userNumberInput, number);
    }

    private void fillUserBirthDate(String dateOfBirth) {
        click(dateOfBirthInput);
        Select selectMonth = new Select(monthSelect);
        String month = DateUtils.getMonth(dateOfBirth);
        selectMonth.selectByContainsVisibleText(month);
        Select selectYear = new Select(yearSelect);
        String year = DateUtils.getYear(dateOfBirth);
        selectYear.selectByValue(year);
        String date = DateUtils.reformatDate(dateOfBirth);
        WebElement dateEl = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(String.format("//*[contains(@aria-label,'%s')]", date))));
        dateEl.click();
    }

    private void fillSubjects(String subject) {
        sendKeys(subjectsInput, subject);
        WebElement subjectBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//div[contains(@class, 'subjects-auto-complete__option') and text()='%s']",
                        subject))));
        subjectBtn.click();
    }

    private void fillHobbiesByValues(List<String> comboboxValues) {
        for (WebElement hobbyCombobox: hobbiesComboboxes) {
            if (comboboxValues.contains(hobbyCombobox.getAttribute("value"))) {
                String id = hobbyCombobox.getAttribute("id");
                WebElement labelConnectedToCheckbox =
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[@for='%s']",
                                id))));
                labelConnectedToCheckbox.click();
            }
        }
    }

    private void uploadPicture(String filePath) {
        File fileToUpload = new File(filePath);
        uploadPicture.sendKeys(fileToUpload.getAbsolutePath());
    }

    private void fillAddress(String address) {
        sendKeys(currentAddressInput, address);
    }

    private void fillState(String state) {
        scrollTo(stateDiv);
        click(stateDiv);
        List<String> statesStr = stateInputOptions.stream()
                .map(WebElement::getText)
                .toList();
        if(!statesStr.contains(state)) {
            throw new NoSuchElementException("State " + state + " is not an option");
        }
        WebElement stateToChooseDiv = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//*[text()='%s']", state))));
        stateToChooseDiv.click();
    }

    private void fillCity(String city) {
        scrollTo(cityDiv);
        click(cityDiv);
        wait.until(ExpectedConditions.visibilityOfAllElements(cityInputOptions));
        List<String> citiesStr = cityInputOptions.stream()
                .map(WebElement::getText)
                .toList();
        if(!citiesStr.contains(city)) {
            throw new NoSuchElementException("City " + city + "is not an option");
        }

        WebElement cityToChooseDiv = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//*[text()='%s']", city))));
        cityToChooseDiv.click();
    }

    public DemoQaFormPage clickSubmitBtn() {
        click(submitBtn);

        return this;
    }

    public String getRandomState() {
        scrollTo(stateDiv);
        click(stateDiv);

        int statesAmount =  stateInputOptions.size();
        int randomStateIndex = NumbersUtils.getRandomNumber(0, statesAmount - 1);
        String state = stateInputOptions.get(randomStateIndex).getText();
        click(stateDiv); //to close drop-down list

        return state;
    }

    public String getRandomCity() {
        scrollTo(cityDiv);
        cityDiv.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(cityInputOptions));

        int citiesAmount =  cityInputOptions.size();
        int randomCityIndex = NumbersUtils.getRandomNumber(0, citiesAmount - 1);

        String city = cityInputOptions.get(randomCityIndex).getText();
        click(cityDiv); //to close drop-down list

        return city;
    }

//    public String getRandomGender() {
//        wait.until(ExpectedConditions.visibilityOfAllElements(genderLabels));
//        int numberOfGenders = gendersCheckboxes.size();
//        int randomGenderIndex = NumbersUtils.getRandomNumber(0, numberOfGenders - 1);
//
//        return gendersCheckboxes.get(randomGenderIndex).getAttribute("value");
//    }

    public List<String> getRandomHobbies() {
        wait.until(ExpectedConditions.visibilityOfAllElements(hobbiesLabels));
        int numberOfHobbies = NumbersUtils.getRandomNumber(1, hobbiesComboboxes.size());
        List<WebElement> copyHobbies = new ArrayList<>(hobbiesComboboxes);
        List<String> hobbies = new ArrayList<>();
        for (int i = 0; i < numberOfHobbies; i++) {
            int index = NumbersUtils.getRandomNumber(0, copyHobbies.size() - 1);
            hobbies.add(copyHobbies.get(index).getAttribute("value"));
            copyHobbies.remove(index);
        }

        return hobbies;
    }

    public String getStudentName() {
        return getText(studentName);
    }

    public String getStudentEmail() {
        return getText(studentEmail);
    }

    public String getGender() {
        return getText(gender);
    }

    public String getMobile() {
        return getText(mobile);
    }

    public String getDateOfBirth() {
        return DateUtils.formatDate(getText(dateOfBirth));
    }

    public String getStateAndCity() {
        return getText(stateAndCity);
    }

    public String getAddress() {
        return getText(address);
    }

    public String getPicturePath() {
        return getText(picture);
    }

    public String getSubjects() {
        return getText(subjects);
    }

    public List<String> getHobbies() {
        return Arrays.stream(hobbies.getText().split(","))
                .map(String::trim)
                .toList();
    }

    public List<String> getHobbiesFromValues(List<String> comboboxValues) {
        List<String> hobbiesNames = new ArrayList<>();
        for (WebElement hobbyCombobox: hobbiesComboboxes) {
            if (comboboxValues.contains(hobbyCombobox.getAttribute("value"))) {
                String id = hobbyCombobox.getAttribute("id");
                WebElement labelConnectedToCheckbox =
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[@for='%s']",
                                id))));
                hobbiesNames.add(labelConnectedToCheckbox.getText());
            }
        }

        return hobbiesNames;
    }

    public DemoQaFormPage fillForm(DemoQaFormData data) {
        fillFirstName(data.getFirstName());
        fillLastName(data.getLastName());
        fillUserEmail(data.getEmail());
        chooseGenderByValue(data.getGender());
        fillUserPhoneNumber(data.getPhoneNumber());
        fillUserBirthDate(data.getBirthDate());
        fillSubjects(data.getSubjects());
        fillHobbiesByValues(data.getHobbies());
        uploadPicture(data.getFilePath());
        fillAddress(data.getAddress());
        fillState(data.getState());
        data.setCity(getRandomCity());
        fillCity(data.getCity());

        return this;
    }

    public String getFileNameFromPath(String filePath) {
        return Paths.get(filePath).getFileName().toString();
    }

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(firstNameInput);
    }


}
