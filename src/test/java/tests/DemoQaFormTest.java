package tests;

import models.DemoQaFormData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DemoQaFormPage;
import utils.ConfigReader;
import utils.DateUtils;
import com.github.javafaker.Faker;

public class DemoQaFormTest extends BaseTest {
    private static final String FILE_PATH = ConfigReader.get("file_path");
    private static final String SUBJECTS = ConfigReader.get("subjects");
    @Test
    public void sendForm() {
        DemoQaFormPage demoQaFormPage = new DemoQaFormPage(driver);
        Faker faker = new Faker();
        DemoQaFormData formData = DemoQaFormData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("Male", "Female", "Other"))
                .phoneNumber(faker.phoneNumber().subscriberNumber(10))
                .birthDate(DateUtils.generateRandomDate())
                .subjects(SUBJECTS)
                .hobbies(demoQaFormPage.getRandomHobbies())
                .filePath(FILE_PATH)
                .address(faker.address().fullAddress())
                .state(demoQaFormPage.getRandomState())
                .build();
        demoQaFormPage.fillForm(formData);
        formData.setCity(demoQaFormPage.getRandomCity());//посмотреть параметры faker
        demoQaFormPage.fillCity(formData.getCity());
        demoQaFormPage.clickSubmitBtn();

        Assert.assertEquals(demoQaFormPage.getStudentName(), formData.getFirstName() + " "
                        + formData.getLastName(), "Name isn't correct");
        Assert.assertEquals(demoQaFormPage.getStudentEmail(), formData.getEmail(), "Email isn't correct");
        Assert.assertEquals(demoQaFormPage.getGender(), formData.getGender(), "Gender isn't  correct");
        Assert.assertEquals(demoQaFormPage.getMobile(), formData.getPhoneNumber(), "Phone number isn't correct");
        Assert.assertEquals(demoQaFormPage.getDateOfBirth(), formData.getBirthDate(), "Date of birth isn't correct");
        Assert.assertEquals(demoQaFormPage.getHobbiesFromValues(formData.getHobbies()), demoQaFormPage.getHobbies(),
                "Hobbies aren't equal");
        Assert.assertEquals(demoQaFormPage.getStateAndCity(), formData.getState() + " " + formData.getCity(),
                "State and city aren't correct");
        Assert.assertEquals(demoQaFormPage.getAddress(), formData.getAddress(), "Address isn't correct");
        Assert.assertEquals(demoQaFormPage.getPicturePath(), demoQaFormPage.getFileNameFromPath(
                formData.getFilePath()), "Picture name isn't correct");
        Assert.assertEquals(demoQaFormPage.getSubjects(), formData.getSubjects(), "Subjects aren't correct");
    }
}
