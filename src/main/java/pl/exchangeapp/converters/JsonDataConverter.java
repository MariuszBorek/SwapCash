package pl.exchangeapp.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.domainnbp.ExchangeRates;
import pl.exchangeapp.domainnbp.Rate;
import pl.exchangeapp.enums.DataFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class JsonDataConverter implements DataConverter {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();

    @Override
    public CurrencyInfo convertData(String data) {
        ExchangeRates exchangeRates = gson.fromJson(data, ExchangeRates.class);
        return createCurrencyInfo(exchangeRates);
    }

    @Override
    public DataFormat getType() {
        return DataFormat.JSON;
    }

    @NotNull
    private CurrencyInfo createCurrencyInfo(ExchangeRates exchangeRates) {
        Rate rate = exchangeRates.getRates().get(0);
        return new CurrencyInfo(
                exchangeRates.getCurrency(),
                rate.getEffectiveDate(),
                rate.getBid(),
                rate.getAsk()
        );
    }
}
