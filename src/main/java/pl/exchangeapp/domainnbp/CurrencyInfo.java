package pl.exchangeapp.domainnbp;


import java.time.LocalDate;

public class CurrencyInfo {
    private String name;
    private LocalDate date;
    private Double buy;
    private Double sell;

    public CurrencyInfo(String name, LocalDate date, Double buy, Double sell) {
        this.name = name;
        this.date = date;
        this.buy = buy;
        this.sell = sell;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getBuy() {
        return buy;
    }

    public Double getSell() {
        return sell;
    }

}
