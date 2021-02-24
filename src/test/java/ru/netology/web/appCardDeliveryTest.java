package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class appCardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
    }


    @Test

    void shouldRegisterByOrderCard() {
        open("http://0.0.0.0:9999/");
        LocalDate date = LocalDate.now();
        LocalDate futureDate = date.plusDays(3);

        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id=date] .input__control").setValue(futureDate.toString());
        $("[data-test-id=name] .input__control").setValue("Иванов Василий");
        $("[data-test-id=phone] .input__control").setValue("+79990980988");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(12));



    }
}
