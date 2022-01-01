package com.domenicoventura.jwdnd.course1.superduperdrive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
    }

    @FindBy(id = "aResultSuccess")
    private WebElement aResultSuccess;

    public void clickOk() {
        wait.until(ExpectedConditions.elementToBeClickable (By.id("aResultSuccess")));
        js.executeScript("arguments[0].click();", aResultSuccess);
    }
}
