package pl.exchangeapp.controler;

import pl.exchangeapp.dao.AccountDAO;
import pl.exchangeapp.dao.AddressDAO;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.entities.Customer;

import java.util.Scanner;

public class ControllerLogin {

    private Scanner in;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private boolean isCorrectPassword = false;

    public ControllerLogin(Scanner in,
                           AddressDAO addressDAO,
                           CustomerDAO customerDAO,
                           AccountDAO accountDAO) {
        this.in = in;
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
    }

    public void loginMenu() {
        System.out.println("enter your phone Number:");
        int phoneNumber = in.nextInt();
        System.out.println("enter password:");
        String password = in.next();
        Customer customer = customerDAO.findByPhoneNumber(phoneNumber);

        if (customer.getPassword().equals(password)) {
            System.out.printf("Welcome %s, your password is correct\n", customer.getFirstName());
            //TODO
        }
    }
}
