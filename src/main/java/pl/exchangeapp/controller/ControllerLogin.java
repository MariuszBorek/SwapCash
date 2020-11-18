package pl.exchangeapp.controller;

import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.dao.AccountDAO;
import pl.exchangeapp.dao.AddressDAO;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.dao.PaymentTransactionDAO;
import pl.exchangeapp.entities.Customer;

import java.io.IOException;
import java.util.Scanner;

public class ControllerLogin {

    private Scanner in;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private PaymentTransactionDAO paymentTransactionDAO;
    private ControllerCustomerDashboard controllerCustomerDashboard;
    private boolean isCorrectPassword = false;

    public ControllerLogin(Scanner in,
                           AddressDAO addressDAO,
                           CustomerDAO customerDAO,
                           AccountDAO accountDAO,
                           PaymentTransactionDAO paymentTransactionDAO) {
        this.in = in;
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.paymentTransactionDAO = paymentTransactionDAO;

        this.controllerCustomerDashboard = new ControllerCustomerDashboard(this.in,
                this.addressDAO,
                this.customerDAO,
                this.accountDAO,
                this.paymentTransactionDAO);
    }

    public void loginMenu(CurrencyApi currencyApi) throws IOException {
        System.out.println("enter your phone Number:");
        String phoneNumber = in.next().replaceAll(" ", "");
        System.out.println(phoneNumber);
        System.out.println("enter password:");
        String password = in.next();
        Customer customer = customerDAO.findByPhoneNumber(Integer.parseInt(phoneNumber));
        if (customer == null) {
            System.out.println("there is no user with this data");
        } else if (customer.getPassword().equals(password)) {
            System.out.printf("Welcome %s, your password is correct\n", customer.getFirstName());
            controllerCustomerDashboard.getDashboard(customer, currencyApi);
        } else {
            System.out.println("something goes wrong, please try again");
            loginMenu(currencyApi);
        }
    }
}
