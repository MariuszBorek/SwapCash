package pl.exchangeapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.conection.ClientHttp;
import pl.exchangeapp.converters.JsonDataConverter;
import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.enums.DataFormat;

public class CurrencyApiTest {
    @Mock
    ClientHttp clientHttp;
    private JsonDataConverter dataConverter;
    private CurrencyApi sut;

    private static final double DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        dataConverter = new JsonDataConverter();
        sut = new CurrencyApi(clientHttp, dataConverter);
    }

    @Test
    public void should_convert_json_data() throws Exception {
        final String jsonString = "{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"173/C/NBP/2020\",\"effectiveDate\":\"2020-09-04\",\"bid\":3.7064,\"ask\":3.7812}]}";

        Mockito.when(clientHttp.executeRequest(Currency.USD, DataFormat.JSON)).thenReturn(jsonString);

        CurrencyInfo currencyInfo = sut.getActualExchangeRateForChosenCurrency(Currency.USD);

        Assert.assertEquals(3.7064, currencyInfo.getSell(), DELTA);
        Assert.assertEquals(3.7812, currencyInfo.getBuy(), DELTA);
        Assert.assertEquals("dolar amerykański", currencyInfo.getName());
        Assert.assertEquals("2020-09-04", currencyInfo.getDate().toString());
    }
}