package pl.exchangeapp;

import org.junit.Before;
import org.junit.Test;
import pl.exchangeapp.converters.JsonDataConverter;
import pl.exchangeapp.domainnbp.CurrencyInfo;

import javax.persistence.Transient;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// EXERCISE: create unit test for JsonDataConverter
public class JsonDataConverterTest {
    JsonDataConverter jsonDataConverter;
    String jsonString;
    CurrencyInfo currencyInfo;

    @Before
    public void create_instance_of_jason_data_converter_class() {
        jsonDataConverter = new JsonDataConverter();
        jsonString = "{\"table\":\"C\",\"currency\":\"euro\",\"code\":\"EUR\",\"rates\":[{\"no\":\"177/C/NBP/2020\",\"effectiveDate\":\"2020-09-10\",\"bid\":4.3995,\"ask\":4.4883}]}";
        currencyInfo = jsonDataConverter.convertData(jsonString);
    }


    @Test
    public void should_return_value_of_bid_field_when_pass_jason_string() {
        Double result = currencyInfo.getBuy();
        Double expect = 4.4883;

        assertNotNull(result);
        assertEquals(expect, result);
    }


    @Test
    public void should_return_value_of_ask_field_when_pass_jason_string() {
        Double result = currencyInfo.getSell();
        Double expect = 4.3995;

        assertNotNull(result);
        assertEquals(expect, result);
    }

    @Test
    public void should_return_value_of_data_field_when_pass_jason_string() {
        LocalDate result = currencyInfo.getDate();
        LocalDate expect = LocalDate.of(2020, 9, 10);

        assertNotNull(result);
        assertEquals(expect, result);
    }

    @Test
    public void should_return_value_of_name_field_when_pass_jason_string() {
        String result = currencyInfo.getName();
        String expect = "euro";

        assertNotNull(result);
        assertEquals(expect, result);
    }



}