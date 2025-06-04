package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DemoQaFormPage;
import utils.DateUtils;
import utils.EmailUtils;
import utils.NumbersUtils;

import java.util.List;
import java.util.UUID;

public class DemoQaFormTest extends BaseTest {
    private static final String FIRST_NAME = UUID.randomUUID().toString().substring(0, 5);
    private static final String LAST_NAME = UUID.randomUUID().toString().substring(0, 5);
    private static final String EMAIL = EmailUtils.generateEmail();
    private static String GENDER;
    private static final String PHONE_NUMBER = NumbersUtils.generate10DigitNumber();
    private static final String DATE = DateUtils.generateRandomDate();
    private static final String SUBJECTS = "Maths";
    private static List<String> HOBBIES;
    private static final String FILE_NAME = "Software testing types.png";
    private static final String FILE_PATH = "C:\\Users\\Pavel\\Downloads\\" + FILE_NAME;
    private static final String ADDRESS = UUID.randomUUID().toString();
    private static String STATE;
    private static String CITY;

    @Test
    public void sendForm() {
        DemoQaFormPage demoQaFormPage = new DemoQaFormPage(driver);
        demoQaFormPage.fillFirstName(FIRST_NAME);
        demoQaFormPage.fillLastName(LAST_NAME);
        demoQaFormPage.fillUserEmail(EMAIL);
        GENDER = demoQaFormPage.getRandomGender();
        demoQaFormPage.chooseGenderByValue(GENDER);
        demoQaFormPage.fillUserPhoneNumber(PHONE_NUMBER);
        demoQaFormPage.fillUserBirthDate(DATE);
        demoQaFormPage.fillSubjects(SUBJECTS);
        HOBBIES = demoQaFormPage.getRandomHobbies();
        demoQaFormPage.fillHobbiesByValues(HOBBIES);
        demoQaFormPage.uploadPicture(FILE_PATH);
        demoQaFormPage.fillAddress(ADDRESS);
        STATE = demoQaFormPage.getRandomState();
        demoQaFormPage.fillState(STATE);
        CITY = demoQaFormPage.getRandomCity();
        demoQaFormPage.fillCity(CITY);
        demoQaFormPage.clickSubmitBtn();
        //Thread.sleep(5000);
        Assert.assertEquals(demoQaFormPage.getStudentName(), FIRST_NAME + " " + LAST_NAME,
                "Name isn't correct");
        Assert.assertEquals(demoQaFormPage.getStudentEmail(), EMAIL, "Email isn't correct");
        Assert.assertEquals(demoQaFormPage.getGender(), GENDER, "Gender isn't  correct");
        Assert.assertEquals(demoQaFormPage.getMobile(), PHONE_NUMBER, "Phone number isn't correct");
        Assert.assertEquals(demoQaFormPage.getDateOfBirth(), DATE, "Date of birth isn't correct");
        Assert.assertEquals(demoQaFormPage.getHobbiesFromValues(HOBBIES), demoQaFormPage.getHobbies(),
                "Hobbies aren't equal");
        Assert.assertEquals(demoQaFormPage.getStateAndCity(), STATE + " " + CITY,
                "State and city aren't correct");
        Assert.assertEquals(demoQaFormPage.getAddress(), ADDRESS, "Address isn't correct");
        Assert.assertEquals(demoQaFormPage.getPicturePath(), FILE_NAME, "Picture name isn't correct");
        Assert.assertEquals(demoQaFormPage.getSubjects(), SUBJECTS, "Subjects aren't correct");
    }
}
