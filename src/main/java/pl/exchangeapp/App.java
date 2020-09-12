
package pl.exchangeapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.dao.CustomerRepository;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.CustomerTransaction;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
       SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CustomerTransaction.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        Customer customer1 = new Customer()
                .setFirstName("Kamil")
                .setLastName("Kowalski")
                .setPassword("12345")
                .build();



        CustomerDAO customerDAO = new  CustomerRepository(sessionFactory);
        customerDAO.createCustomer(customer1);
        System.out.println(customer1);






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
