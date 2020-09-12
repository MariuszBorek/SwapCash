package pl.exchangeapp.domainnbp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ExchangeRates {

    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("rates")
    @Expose
    private List<Rate> rates = new ArrayList<Rate>();

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rate> getRates() {
        return rates;
    }
}
