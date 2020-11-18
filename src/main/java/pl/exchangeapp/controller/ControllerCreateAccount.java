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
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("enter your phone number");
            String phoneNumber = in.next().replaceAll(" ", "");
            if(phoneNumber.matches("\\D*")) {
                System.out.println("illegal sign, please try again");
                isTrue = false;
                createAccountMenu();
                return;
            }
            System.out.println("password");
            String password = in.next();
            System.out.println("first name");
            String firstName = in.next();
            System.out.println("last name");
            String lastName = in.next();

            // account
            Currency typeOfAccount = Currency.PLN;
            BigDecimal balance = new BigDecimal("1000");

            // address
            System.out.println("street name");
            String street = in.next();
            System.out.println("house no.");
            String number = in.next();
            System.out.println("postal code");
            String postalCode = in.next();
            System.out.println("city");
            String city = in.next();

            Address address = new Address()
                    .withStreet(street)
                    .withNumber(number)
                    .withCity(city)
                    .withPostalCode(postalCode)
                    .build();

            Account account = new Account()
                    .withTypeOfAccount(typeOfAccount)
                    .withBalance(balance)
                    .build();

            Customer customer = new Customer()
                    .withPhoneNumber(Integer.parseInt(phoneNumber))
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withPassword(password)
                    .withAccounts(List.of(account))
                    .withAddress(address)
                    .build();

            addressDAO.createAddress(address);
            accountDAO.createAccount(account);
            customerDAO.createCustomer(customer);

            System.out.println("success! you got 1000 PLN on start.");
            System.out.println("[press any letter and enter to go]");
            String goBackToDashboard = in.next();
            return;
        }
    }
}
