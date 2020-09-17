package pl.exchangeapp.controller;

import pl.exchangeapp.enums.Currency;

public class GraphicalInterface {
    private static GraphicalInterface graphicalInterface = null;

    private GraphicalInterface() {}

    public static GraphicalInterface getInstance() {
        if (graphicalInterface == null)
            graphicalInterface = new GraphicalInterface();

        return graphicalInterface;
    }

    private char sign = '-';
    private int numberOfSign = 100;

    // ------------------------------------------
    public void drawLine() {
        line();
    }

    public void drawLine(int numberOfSign) {
        this.numberOfSign = numberOfSign;
        line();
    }

    private void line() {
        for (int i = 0; i < numberOfSign; i++) {
            System.out.print(sign);
        }
        System.out.println();
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    // ------------------------------------------
    public void displayCurrenciesInLine() {
        for (Currency currency : Currency.values()) {
            System.out.print(currency.name() + "; ");
        }
    }

}
