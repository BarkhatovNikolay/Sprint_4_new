package ru.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    // Локаторы главной страницы
    private static final By TOP_ORDER_BUTTON = By.xpath(".//button[contains(text(), 'Заказать')]");
    private static final By BOTTOM_ORDER_BUTTON = By.xpath("(.//button[contains(text(), 'Заказать')])[2]");


    // Локаторы вопросов
    private static final String[] QUESTION_LOCATORS = {
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"
    };

    // Локаторы ответов
    private static final String[] ANSWER_LOCATORS = {
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"
    };

    public By getQuestionLocator(int index) {
        return By.id(QUESTION_LOCATORS[index]);
}
    public By getAnswerLocator(int index){
        return By.id(ANSWER_LOCATORS[index]);
    }


    // нажатие кнопки снизу либо сверху
    public void clickOrderButton(boolean fromTop) {
        if (fromTop) {
            driver.findElement(TOP_ORDER_BUTTON).click();
        } else {
            WebElement bottomButton = driver.findElement(BOTTOM_ORDER_BUTTON);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", bottomButton);
            bottomButton.click();
        }
    }

    public void clickQuestion(By questionLocator) {
        driver.findElement(questionLocator).click();
    }

    public String getAnswerText(By answerLocator) {
        return driver.findElement(answerLocator).getText();
    }
    public String getQuestionText(By questionLocator) {
        return driver.findElement(questionLocator).getText();
    }

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
