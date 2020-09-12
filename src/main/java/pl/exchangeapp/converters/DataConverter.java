package pl.exchangeapp.converters;

import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.enums.DataFormat;

public interface DataConverter {
    CurrencyInfo convertData(String data);

    DataFormat getType();
}
