package com.example.universityproject.backend;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


public class WebParser {


    public static void main(String[] args) {

        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = driver.getTitle();
        System.out.println(title);
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.className("button"));
        WebElement message = driver.findElement(By.id("message"));
        textBox.sendKeys("Selenium");
        submitButton.click();
        String value = message.getText();
        System.out.println(value);

    }
}
