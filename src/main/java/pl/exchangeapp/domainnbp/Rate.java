package pl.exchangeapp.domainnbp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Rate {
    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("effectiveDate")
    @Expose
    private LocalDate effectiveDate;
    @SerializedName("bid")
    @Expose
    private Double bid;
    @SerializedName("ask")
    @Expose
    private Double ask;
    @SerializedName("mid")
    @Expose
    private Double mid;


    public String getNo() {
        return no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Double getMid() {
        return mid;
    }
}
