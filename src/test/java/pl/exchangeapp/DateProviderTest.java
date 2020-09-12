package pl.exchangeapp;

import org.junit.Assert;
import org.junit.Test;
import pl.exchangeapp.providers.DateProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateProviderTest {


    @Test
    public void should_return_friday_when_pass_saturday() {
        LocalDate currentDate = LocalDate.of(2020, 9, 5);
        String result = DateProvider.getLastWorkingDateAsString(currentDate);

        Assert.assertEquals("2020-09-04", result);
    }

    @Test
    public void should_return_friday_when_pass_sunday() {
        LocalDate currentDate = LocalDate.of(2020, 9, 6);
        String result = DateProvider.getLastWorkingDateAsString(currentDate);

        Assert.assertEquals("2020-09-04", result);
    }

    @Test
    public void should_return_the_same_date() {
        LocalDate currentDate = LocalDate.of(2020, 9, 3);
        String result = DateProvider.getLastWorkingDateAsString(currentDate);

        Assert.assertEquals("2020-09-03", result);
    }

    @Test
    public void should_return_the_last_proper_date() {
        String result = DateProvider.getLastWorkingDateAsString(null);
        String expected = getCurrentExpectedDate();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void should_return_the_last_proper_date_based_on_future_date() {
        LocalDate currentDate = LocalDate.of(2030, 9, 7);
        String result = DateProvider.getLastWorkingDateAsString(currentDate);
        String expected = getCurrentExpectedDate();

        Assert.assertEquals(expected, result);
    }

    private String getCurrentExpectedDate() {
        LocalDate aktualnaData = LocalDate.now();
        if (aktualnaData.getDayOfWeek() == DayOfWeek.SATURDAY) {
            aktualnaData = aktualnaData.minusDays(1);
        } else if (aktualnaData.getDayOfWeek() == DayOfWeek.SUNDAY) {
            aktualnaData = aktualnaData.minusDays(2);
        }

        return aktualnaData.toString();
    }
}