package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

@Tag("registrationPage")
public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            registrationPage.setFirstName("Dmitrii")
                    .setLastName("Borovkov")
                    .setEmail("dmitry@borovkov.com")
                    .setGender("Male")
                    .setUserPhoneNumber("9269262626")
                    .setDateOfBirth("30", "July", "2008")
                    .setSubjects("Math")
                    .setHobbiesWrapper("Music")
                    .uploadPicture("brain.jpg")
                    .setAddress("The Street, 9")
                    .setState("NCR")
                    .setCity("Noida")
                    .submitForm();
        });
        step("Verify results", () -> {
            registrationPage.checkResult("Student Name", "Dmitrii Borovkov")
                .checkResult("Student Email", "dmitry@borovkov.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9269262626")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Music")
                .checkResult("Picture", "brain.jpg")
                .checkResult("Address", "The Street, 9")
                .checkResult("State and City", "NCR Noida");});
    }

    @Test
    void fillFormWithMinimumData() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .removeBanner()
                .setFirstName("Dmitrii")
                .setLastName("Borovkov")
                .setGender("Male")
                .setUserPhoneNumber("9269262626")
                .setDateOfBirth("30", "July", "2008")
                .submitForm();

        registrationPage.checkResult("Student Name", "Dmitrii Borovkov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9269262626")
                .checkResult("Date of Birth", "30 July,2008");

    }

    @Test
    void fillFormNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .removeBanner()
                .setFirstName("Dmitrii")
                .setLastName("Borovkov")
                .setUserPhoneNumber("7999888770")
                .setDateOfBirth("30", "July", "2008")
                .submitForm();

        registrationPage.checkFillFormWithoutGender();
    }
}