package allTests;

import config.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.yandex.pages.MainPage;
import testData.TestDataFAQ;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;


public class FaqTest extends Configuration {

    private void checkQuestionAndAnswer(MainPage mainPage, By questionLocator, String expectedQuestion,
                                        By answerLocator, String expectedAnswer){
        WebElement questionElement = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);

        mainPage.waitForElementVisible(questionLocator);
        String actualQuestion = mainPage.getQuestionText(questionLocator);
        assertEquals("Текст вопроса не соответствует", expectedQuestion, actualQuestion);

        mainPage.clickQuestion(questionLocator);

        mainPage.waitForElementVisible(answerLocator);
        String actualAnswer = mainPage.getAnswerText(answerLocator);
        assertEquals("Текст ответа не соответствует", expectedAnswer, actualAnswer);
    }

    //тест
    @Test
    public void testSectionQuestionsAndAnswers(){
        MainPage mainPage = new MainPage(driver);

        for (int i = 0; i < TestDataFAQ.EXPECTED_QUESTIONS.length; i++){
            By questionLocator = By.id(MainPage.QUESTION_LOCATORS[i]);
            By answerLocator = By.id(MainPage.ANSWER_LOCATORS[i]);
            checkQuestionAndAnswer(mainPage, questionLocator, TestDataFAQ.EXPECTED_QUESTIONS[i],
                    answerLocator, TestDataFAQ.EXPECTED_ANSWERS[i]);
        }
    }

}
