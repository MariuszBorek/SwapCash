
package pl.exchangeapp;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.dao.*;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;
import pl.exchangeapp.enums.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        // create connection with Database
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // create instances of repositories
        AddressDAO addressDAO = new AddressRepository(databaseConnection);
        CustomerDAO customerDAO = new CustomerRepository(databaseConnection);
        AccountDAO accountDAO = new AccountRepository(databaseConnection);
        CustomerTransactionDAO customerTransactionDAO = new CustomerTransactionRepository(databaseConnection);


        // create objects
//        List<Address> addresses = new ArrayList<>();
//        List<Account> accounts = new ArrayList<>();
//        List<Customer> customers = new ArrayList<>();
//        List<CustomerTransaction> customerTransactions = new ArrayList<>();

        // address objects

        Address address1 = new Address()
                .withStreet("Błotna")
                .withNumber("5b")
                .withCity("Warszawa")
                .withPostalCode("33-456")
                .build();

        Address address2 = new Address()
                .withStreet("Wojska Polskiego")
                .withNumber("43")
                .withCity("Kraków")
                .withPostalCode("36-547")
                .build();

        // account objects

        Account account1 = new Account()
                .withAccountNumber(10001L)
                .withTypeOfAccount(Currency.PLN)
                .withBalance(new BigDecimal(10_000))
                .build();

        Account account2 = new Account()
                .withAccountNumber(10002L)
                .withTypeOfAccount(Currency.EUR)
                .withBalance(new BigDecimal(20_000))
                .build();

        // transaction objects

        PaymentTransaction transaction1 = new PaymentTransaction()
                .withAccount(account1)
                .withDate(LocalDate.now())
                .withAmount(new BigDecimal(543))
                .build();

        PaymentTransaction transaction2 = new PaymentTransaction()
                .withAccount(account2)
                .withDate(LocalDate.of(2020, 5, 23))
                .withAmount(new BigDecimal(234))
                .build();

        // customer objects

        Customer customer1 = new Customer()
                .withPhoneNumber(567_345_234)
                .withFirstName("Wacław")
                .withLastName("Kowalski")
                .withPassword("12345")
                .withAccounts(List.of(account1))
                .withAddress(address1)
                .build();

        Customer customer2 = new Customer()
                .withPhoneNumber(658_345_964)
                .withFirstName("Justyna")
                .withLastName("Nowak")
                .withPassword("qwerty")
                .withAccounts(List.of(account2))
                .withAddress(address2)
                .build();

        // insert objects to database

        addressDAO.createAddress(address1);
        addressDAO.createAddress(address2);

        accountDAO.createAccount(account1);
        accountDAO.createAccount(account2);

        customerTransactionDAO.createCustomerTransaction(transaction1);
        customerTransactionDAO.createCustomerTransaction(transaction2);

        customerDAO.createCustomer(customer1);
        customerDAO.createCustomer(customer2);

        // find customer by phone number
        customerDAO.findByPhoneNumber(658_345_964);

        // update objects
//        customer2.withFirstName("Justyna CHANGED")
//                .withLastName("Nowak CHANGED")
//                .withPassword("qwerty CHANGED")
//                .build();
//        customerDAO.updateCustomer(customer2, 658_345_964);

        // findByName
//        customerDAO.findByName("Justyna");

        // update without phoneNumber
//        customer1.withFirstName("Wacław CHANGED")
//                .build();
//        customerDAO.updateCustomer(customer1);

        // delete Customer by phone number
//        customerDAO.deleteCustomerWithAllInfoAboutThem(658_345_964);

        // delete Customer address
//        addressDAO.deleteAddress(address2);

        // transfer money
        accountDAO.transferMoney(new BigDecimal(333), 658_345_964, 567_345_234);











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
