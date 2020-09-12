package pl.exchangeapp;
import okhttp3.Request;
import org.junit.Assert;
import org.junit.Test;
import pl.exchangeapp.conection.RequestBuilder;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.enums.DataFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RequestBuilderTest {
    @Test
    public void should_build_request_with_default_parameters() {
        final Request result = new RequestBuilder().build();
        String expected = "http://api.nbp.pl/api/exchangerates/rates/c/EUR/" + getCurrentExpectedDate() + "?format=JSON";
        Assert.assertEquals(result.url().toString(), expected);
    }

    @Test
    public void should_build_request_with_default_currency_when_pass_null_parameter() {
        final Request result = new RequestBuilder()
                .currency(null)
                .build();
        String expected = "http://api.nbp.pl/api/exchangerates/rates/c/EUR/" + getCurrentExpectedDate() + "?format=JSON";
        Assert.assertEquals(result.url().toString(), expected);
    }

    @Test
    public void should_build_request_with_default_data_format_when_pass_null_parameter() {
        final Request result = new RequestBuilder()
                .dataFormat(null)
                .build();
        String expected = "http://api.nbp.pl/api/exchangerates/rates/c/EUR/" + getCurrentExpectedDate() + "?format=JSON";
        Assert.assertEquals(result.url().toString(), expected);
    }

    @Test
    public void should_build_request_with_default_date_when_pass_null_parameter() {
        final Request result = new RequestBuilder()
                .date(null)
                .build();
        String expected = "http://api.nbp.pl/api/exchangerates/rates/c/EUR/" + getCurrentExpectedDate() + "?format=JSON";
        Assert.assertEquals(result.url().toString(), expected);
    }

    @Test
    public void should_build_request_with_all_currency() {
        for (Currency currency : Currency.values()) {
            final Request result = new RequestBuilder().currency(currency).build();
            String expected = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency.name() + "/";
            Assert.assertTrue(result.url().toString().startsWith(expected));
        }
    }

    @Test
    public void should_build_request_with_good_date() {
        LocalDate date = LocalDate.of(2020, 9, 4);
        final Request result = new RequestBuilder().date(date).build();
        String expected = "http://api.nbp.pl/api/exchangerates/rates/c/EUR/2020-09-04?format=JSON";
        Assert.assertEquals(result.url().toString(), expected);
    }

    @Test
    public void should_build_request_with_data_format() {
        final Request result = new RequestBuilder().dataFormat(DataFormat.JSON).build();
        String expected = "?format=JSON";
        Assert.assertTrue(result.url().toString().endsWith(expected));
    }

    @Test
    public void should_build_request_with_XML_as_data_format() {
        final Request result = new RequestBuilder().dataFormat(DataFormat.XML).build();
        String expected = "?format=XML";
        Assert.assertTrue(result.url().toString().endsWith(expected));
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