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
    public static final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    public static final By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By inputStation = By.xpath(".//input[@placeholder='* Станция метро']");
    public static final By selectStation = By.xpath(".//.//div[@class='select-search__select']//li");
    public static final By inputPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Next
    public static final By buttonText = By.xpath(".//button[text()='Далее']");
    //вторая часть
    public static final By inputDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By dropdownDate = By.xpath(".//div[text()='* Срок аренды']");
    public static final By checkBoxColour = By.xpath(".//input[@type='checkbox']");
    public static final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    public static final By buttonOrder = By.xpath("(//button[text()='Заказать'])[2]");
    //Третья часть
    public static final By confirmWindow = By.xpath(".//div[contains(@class, 'Order_Modal')]");
    public static final By buttonYes = By.xpath(".//button[text()='Да']");
    public static final By succes = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    //Методы взаимодействия с локаторами
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void firstWindowFill(String name, String surname, String address, String station, String phone){
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
        driver.findElement(inputAddress).sendKeys(address);

        WebElement stationInput = driver.findElement(inputStation);
        scrollToElement(stationInput);
        stationInput.click();
        stationInput.sendKeys(station);

        driver.findElement(selectStation).click();
        driver.findElement(inputPhoneNumber).sendKeys(phone);

        WebElement nextButton = driver.findElement(buttonText);
        scrollToElement(nextButton);
        nextButton.click();
    }

    public void secondWindowFill(String date, String rentalPeriod, String colour, String comment) {
        //Заполняем дату
        WebElement dateInput = driver.findElement(inputDate);
        scrollToElement(dateInput);
        dateInput.sendKeys(date);
        driver.findElement(By.xpath(".//div[contains(text(), 'Про аренду')]")).click();

        //Выбираем срок аренды
        WebElement rentalDropdown = driver.findElement(dropdownDate);
        scrollToElement(rentalDropdown);
        rentalDropdown.click();

        WebElement periodOption = driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']"));
        scrollToElement(periodOption);
        periodOption.click();

        //Выбор цвета
        if (colour.equals("black")) {
            WebElement blackCheckbox = driver.findElements(checkBoxColour).get(0);
            scrollToElement(blackCheckbox);
            blackCheckbox.click();
        } else if (colour.equals("grey")) {
            WebElement greyCheckbox = driver.findElements(checkBoxColour).get(1);
            scrollToElement(greyCheckbox);
            greyCheckbox.click();
        }

        //комментарий
        WebElement commentInput = driver.findElement(inputComment);
        scrollToElement(commentInput);
        commentInput.sendKeys(comment);

        //Кнопка заказа
        WebElement orderButton = driver.findElement(buttonOrder);
        scrollToElement(orderButton);
        orderButton.click();
    }

    public void confirmOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmWindow));
        driver.findElement(buttonYes).click();
    }

    public String getSuccess(){
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(succes));
        return driver.findElement(succes).getText();
    }
}

