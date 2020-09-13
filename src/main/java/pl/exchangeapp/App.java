
package pl.exchangeapp;

import pl.exchangeapp.conection.DataBaseConnection;
import pl.exchangeapp.dao.*;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        DataBaseConnection dataBaseConnection = new DataBaseConnection();

        AddressDAO addressDAO = new AddressRepository(dataBaseConnection);
        CustomerDAO customerDAO = new CustomerRepository(dataBaseConnection);
        AccountDAO accountDAO = new AccountRepository(dataBaseConnection);

        List<Address> addresses = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        addresses.add(new Address()
                .setStreet("Błotna")
                .setNumber("5b")
                .setCity("Warszawa")
                .setPostalCode("33-456")
                .build());

        addresses.add(new Address()
                .setStreet("Wojska Polskiego")
                .setNumber("43")
                .setCity("Kraków")
                .setPostalCode("36-547")
                .build());

        // ---------------------------------------------------

        accounts.add(new Account()
                .setAccountNumber(10000000000000L)
                .setBalance(new BigDecimal(10000))
                .build());

        accounts.add(new Account()
                .setAccountNumber(20000000000000L)
                .setBalance(new BigDecimal(20000))
                .build());

        // ---------------------------------------------------

        customers.add(new Customer()
                .setFirstName("Wacław")
                .setLastName("Gajdosz")
                .setPassword("12345")
                .setAccounts(List.of(accounts.get(0)))
                .setAddress(addresses.get(0))
                .build());

        customers.add(new Customer()
                .setFirstName("Barbara")
                .setLastName("Grzyb")
                .setPassword("qwerty")
                .setAccounts(List.of(accounts.get(1)))
                .setAddress(addresses.get(1))
                .build());

        // ---------------------------------------------------

        addressDAO.createAddress(addresses.get(0));
        addressDAO.createAddress(addresses.get(1));

        accountDAO.createAccount(accounts.get(0));
        accountDAO.createAccount(accounts.get(1));

        customerDAO.createCustomer(customers.get(0));
        customerDAO.createCustomer(customers.get(1));


        // CURRENCYAPI

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

        //
//        System.out.println("Podaj walute transakcji opcje");
//        for (Currency currency : Currency.values()) {
//            System.out.print(currency.name() + "; ");
//        }


    }
}
