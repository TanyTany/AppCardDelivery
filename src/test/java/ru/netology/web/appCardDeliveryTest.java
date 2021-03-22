package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class appCardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test

    void shouldRegisterByOrderCard() {
        open("http://0.0.0.0:9999/");

        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id=date] .input__control").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        String futureDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] .input__control").setValue(futureDate);
        $("[data-test-id=name] .input__control").setValue("Иванов Василий");
        $("[data-test-id=phone] .input__control").setValue("+79990980988");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(12));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на "+ futureDate));



    }
}
