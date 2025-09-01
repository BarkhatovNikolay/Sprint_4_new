package ru.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Первая часть
    public static final By INPUT_NAME = By.xpath(".//input[@placeholder='* Имя']");
    public static final By INPUT_SURNAME = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By INPUT_ADRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By INPUT_STATION = By.xpath(".//input[@placeholder='* Станция метро']");
    public static final By SELECT_STATION = By.xpath(".//.//div[@class='select-search__select']//li");
    public static final By INPUT_PHONE_NUMBER = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Next
    public static final By BUTTON_NEXT = By.xpath(".//button[text()='Далее']");
    //вторая часть
    public static final By INPUT_DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By DROPDOWN_RENTAL = By.xpath(".//div[text()='* Срок аренды']");
    public static final By CHECKBOX_COLOUR = By.xpath(".//input[@type='checkbox']");
    public static final By INPUT_COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    public static final By BUTTON_ORDER = By.xpath("(//button[text()='Заказать'])[2]");
    //Третья часть
    public static final By CONFIRM_WINDOW = By.xpath(".//div[contains(@class, 'Order_Modal')]");
    public static final By YES_BUTTON = By.xpath(".//button[text()='Да']");
    public static final By SUCCESS = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    //Методы взаимодействия с локаторами
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void firstWindowFill(String name, String surname, String address, String station, String phone){
        driver.findElement(INPUT_NAME).sendKeys(name);
        driver.findElement(INPUT_SURNAME).sendKeys(surname);
        driver.findElement(INPUT_ADRESS).sendKeys(address);

        WebElement stationInput = driver.findElement(INPUT_STATION);
        scrollToElement(stationInput);
        stationInput.click();
        stationInput.sendKeys(station);

        driver.findElement(SELECT_STATION).click();
        driver.findElement(INPUT_PHONE_NUMBER).sendKeys(phone);

        WebElement nextButton = driver.findElement(BUTTON_NEXT);
        scrollToElement(nextButton);
        nextButton.click();
    }

    public void secondWindowFill(String date, String rentalPeriod, String colour, String comment) {
        //Заполняем дату
        WebElement dateInput = driver.findElement(INPUT_DATE);
        scrollToElement(dateInput);
        dateInput.sendKeys(date);
        driver.findElement(By.xpath(".//div[contains(text(), 'Про аренду')]")).click();

        //Выбираем срок аренды
        WebElement rentalDropdown = driver.findElement(DROPDOWN_RENTAL);
        scrollToElement(rentalDropdown);
        rentalDropdown.click();

        WebElement periodOption = driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']"));
        scrollToElement(periodOption);
        periodOption.click();

        //Выбор цвета
        if (colour.equals("black")) {
            WebElement blackCheckbox = driver.findElements(CHECKBOX_COLOUR).get(0);
            scrollToElement(blackCheckbox);
            blackCheckbox.click();
        } else if (colour.equals("grey")) {
            WebElement greyCheckbox = driver.findElements(CHECKBOX_COLOUR).get(1);
            scrollToElement(greyCheckbox);
            greyCheckbox.click();
        }

        //комментарий
        WebElement commentInput = driver.findElement(INPUT_COMMENT);
        scrollToElement(commentInput);
        commentInput.sendKeys(comment);

        //Кнопка заказа
        WebElement orderButton = driver.findElement(BUTTON_ORDER);
        scrollToElement(orderButton);
        orderButton.click();
    }

    public void confirmOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_WINDOW));
        driver.findElement(YES_BUTTON).click();
    }

    public String getSuccess(){
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(SUCCESS));
        return driver.findElement(SUCCESS).getText();
    }
}

