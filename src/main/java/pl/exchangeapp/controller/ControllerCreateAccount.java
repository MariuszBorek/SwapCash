package pl.exchangeapp.controller;

import pl.exchangeapp.dao.AccountDAO;
import pl.exchangeapp.dao.AddressDAO;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ControllerCreateAccount {
    private Scanner in;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;


    public ControllerCreateAccount(Scanner in,
                                   AddressDAO addressDAO,
                                   CustomerDAO customerDAO,
                                   AccountDAO accountDAO) {
        this.in = in;
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
    }

    public void createAccountMenu() {
        while (true) {
            System.out.println("enter your data");
//            String text = null;
//            text = in.next();

            // customer
            System.out.println("wprowadź numer telefonu");
            int phoneNumber = in.nextInt();
            String password = "qwerty";
            String firstName = "John";
            String lastName = "Lennon";

            // account
            System.out.println("wprowadź numer konta, rozwiazanie na czas testow");
            int accountNumber = in.nextInt();
            Currency typeOfAccount = Currency.PLN;
            BigDecimal balance = new BigDecimal(1000);

            // address
            String street = "Z. Lenartowicza";
            String number = "3b";
            String postalCode = "33-345";
            String city = "Warszawa";

            Address address = new Address()
                    .withStreet(street)
                    .withNumber(number)
                    .withCity(city)
                    .withPostalCode(postalCode)
                    .build();

            Account account = new Account()
                    .withAccountNumber(accountNumber)
                    .withTypeOfAccount(typeOfAccount)
                    .withBalance(balance)
                    .build();

            Customer customer = new Customer()
                    .withPhoneNumber(phoneNumber)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withPassword(password)
                    .withAccounts(List.of(account))
                    .withAddress(address)
                    .build();

            addressDAO.createAddress(address);
            accountDAO.createAccount(account);
            customerDAO.createCustomer(customer);

            System.out.println("insert data automatically");
            return;
        }
    }
}
