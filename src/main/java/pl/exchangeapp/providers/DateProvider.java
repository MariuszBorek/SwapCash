package pl.exchangeapp.providers;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateProvider {
    private DateProvider() {
    }

    public static LocalDate getLastWorkingDate(LocalDate currentDate) {
        if(currentDate == null || currentDate.isAfter(LocalDate.now())) {
            currentDate = LocalDate.now();
        }
        if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            currentDate = currentDate.minusDays(1);
        } else if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            currentDate = currentDate.minusDays(2);
        }
        return currentDate;
    }

    public static String getLastWorkingDateAsString(LocalDate currentDate) {
        return getLastWorkingDate(currentDate).toString();

    }
}
