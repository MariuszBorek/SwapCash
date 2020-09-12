package pl.exchangeapp.api;

import pl.exchangeapp.conection.Client;
import pl.exchangeapp.converters.DataConverter;
import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.enums.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurrencyApi {
    private final DataConverter dataConverter;
    private final Client client;

    public CurrencyApi(Client client, DataConverter dataConverter) {
        this.client = client;
        this.dataConverter = dataConverter;

    }

    public CurrencyInfo getActualExchangeRateForChosenCurrency(Currency currency) throws IOException {
        String dataString = client.executeRequest(currency, dataConverter.getType());
        return dataConverter.convertData(dataString);
    }

    public List<CurrencyInfo> getListOfActualExchangeRates() throws IOException {
        List<CurrencyInfo> listOfActualExchangeRates = new ArrayList<>();
        List<Currency> list = List.of(Currency.values());
        for (int i = 0; i < Currency.values().length; i++) {
            String dataString = client.executeRequest(list.get(i), dataConverter.getType());
            listOfActualExchangeRates.add(dataConverter.convertData(dataString));
        }
        return listOfActualExchangeRates;
    }

    public BigDecimal buyMoneyFromUser(Currency currency, BigDecimal valueOfMoneyToExchange) throws IOException {
        String dataString = client.executeRequest(currency, dataConverter.getType());
        CurrencyInfo currencyInfo = dataConverter.convertData(dataString);
        return valueOfMoneyToExchange.multiply(BigDecimal.valueOf(currencyInfo.getBuy()));
    }

    public BigDecimal sellMoneyToUser(Currency currency, BigDecimal valueOfMoneyToExchange) throws IOException {
        String dataString = client.executeRequest(currency, dataConverter.getType());
        CurrencyInfo currencyInfo = dataConverter.convertData(dataString);
        return valueOfMoneyToExchange.multiply(BigDecimal.valueOf(currencyInfo.getSell()));
    }

}

