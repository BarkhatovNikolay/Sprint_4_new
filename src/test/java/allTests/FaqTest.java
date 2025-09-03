package allTests;

import config.Configuration;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.yandex.pages.MainPage;
import testData.TestDataFAQ;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest extends Configuration {

    private final int index;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public FaqTest(int index, String expectedQuestion, String expectedAnswer) {
        this.index = index;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {0, TestDataFAQ.EXPECTED_QUESTIONS[0], TestDataFAQ.EXPECTED_ANSWERS[0]},
                {1, TestDataFAQ.EXPECTED_QUESTIONS[1], TestDataFAQ.EXPECTED_ANSWERS[1]},
                {2, TestDataFAQ.EXPECTED_QUESTIONS[2], TestDataFAQ.EXPECTED_ANSWERS[2]},
                {3, TestDataFAQ.EXPECTED_QUESTIONS[3], TestDataFAQ.EXPECTED_ANSWERS[3]},
                {4, TestDataFAQ.EXPECTED_QUESTIONS[4], TestDataFAQ.EXPECTED_ANSWERS[4]},
                {5, TestDataFAQ.EXPECTED_QUESTIONS[5], TestDataFAQ.EXPECTED_ANSWERS[5]},
                {6, TestDataFAQ.EXPECTED_QUESTIONS[6], TestDataFAQ.EXPECTED_ANSWERS[6]},
                {7, TestDataFAQ.EXPECTED_QUESTIONS[7], TestDataFAQ.EXPECTED_ANSWERS[7]},
        };
    }
    @Test
    public void testSectionQuestionsAndAnswers() {
        MainPage mainPage = new MainPage(driver);
        By questionLocator = mainPage.getQuestionLocator(index);
        By answerLocator = mainPage.getAnswerLocator(index);

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

}


    /*private void checkQuestionAndAnswer(MainPage mainPage, By questionLocator, String expectedQuestion,
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
*/