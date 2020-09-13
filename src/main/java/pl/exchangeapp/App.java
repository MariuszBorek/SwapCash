
package pl.exchangeapp;

import pl.exchangeapp.conection.DataBaseConnection;
import pl.exchangeapp.dao.CustomerRepository;
import pl.exchangeapp.entities.Customer;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        CustomerRepository customerRepository = new CustomerRepository(dataBaseConnection);

        Customer customer = new Customer()
                .setFirstName("Wacław")
                .setLastName("Gajdosz")
                .setPassword("12345")
                .build();
        customerRepository.createCustomer(customer);

        customer = new Customer()
                .setFirstName("Barbara")
                .setLastName("Grzyb")
                .setPassword("qwerty")
                .build();
        customerRepository.createCustomer(customer);







//        CurrencyApi api = new CurrencyApi(Client.getInstance(), new JsonDataConverter());

//        // get single currency
//        System.out.println("buy: " + api.getActualExchangeRateForChosenCurrency(Currency.USD).getBuy());
//        System.out.println("sell: " + api.getActualExchangeRateForChosenCurrency(Currency.USD).getSell());
//
//        System.out.println();
//
//        // get list of all rates
//        List<CurrencyInfo> currencyRates = api.getListOfActualExchangeRates();
//        for(CurrencyInfo currency: currencyRates) {
//            System.out.printf("%s: Buy: %s, Sell: %s\n",currency.getName(), currency.getBuy(), currency.getSell());
//        }
//
//        // sell money User Sell)
//        System.out.println(api.buyMoneyFromUser(Currency.USD, new BigDecimal(100)));
//        System.out.println(api.sellMoneyToUser(Currency.USD, new BigDecimal(100)));









    }
}

//
//        System.out.println("Podaj walute transakcji opcje");
//        for (Currency currency : Currency.values()) {
//            System.out.print(currency.name() + "; ");
//        }
//
//        System.out.println();
//        Currency currency = Currency.getCurrency(scanner.nextLine());
//
