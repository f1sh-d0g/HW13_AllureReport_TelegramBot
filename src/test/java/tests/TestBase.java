package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
        @BeforeAll
        static void setupConfiguration() {
            Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");

            String Remote = System.getProperty("remote", "selenoid.autotests.cloud");
            Configuration.remote = "https://user1:1234@" + Remote + "/wd/hub";

            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserVersion = System.getProperty("browserVersion", "126.0");
            Configuration.browserSize = System.getProperty("browserSize", "1920x2160");
            Configuration.pageLoadStrategy = "eager";



            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }
}