package pl.exchangeapp.enums;

public enum Currency {
    USD,
    AUD,
    CAD,
    EUR,
    HUF,
    CHF,
    GBP,
    JPY,
    CZK,
    DKK,
    NOK,
    SEK,
    XDR;

    public static Currency getCurrency(String currencyName) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equalsIgnoreCase(currencyName)) {
                return currency;
            }
        }
        return USD;
    }
}
