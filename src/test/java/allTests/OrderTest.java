package allTests;

import config.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.pages.MainPage;
import ru.yandex.pages.OrderPage;
import testData.TestDataOrder;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends Configuration {

    private final boolean mainOrderButtons;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    //конструктор
    public OrderTest(boolean mainOrderButtons, String name, String surname, String address, String station, String phone, String date, String rentalPeriod, String colour, String comment) {
        this.mainOrderButtons = mainOrderButtons;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(TestDataOrder.getTestOrderData());
    }
    @Test
    public void testOrderAllPositiveFlow(){
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickOrderButton(mainOrderButtons);
        orderPage.firstWindowFill(name, surname, address, station, phone);
        orderPage.secondWindowFill(date, rentalPeriod, colour, comment);
        orderPage.confirmOrder();

        String succes = orderPage.getSuccess();
        assertTrue("Нет сообщения об успешном заказе", succes.contains("Заказ оформлен"));
    }
}

