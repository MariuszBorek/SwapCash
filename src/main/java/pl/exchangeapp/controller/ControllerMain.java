package pl.exchangeapp.controller;

import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.dao.*;

import java.io.IOException;
import java.util.Scanner;

public class ControllerMain implements Controller {
    private ScannerProvider scannerProvider = ScannerProvider.getInstance();
    private Scanner in = scannerProvider.getScanner();

    private CurrencyApi currencyApi;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private PaymentTransactionDAO paymentTransactionDAO;
    private ControllerCreateAccount controllerCreateAccount;
    private ControllerLogin controllerLogin;
    private GraphicalInterface graphicalInterface = GraphicalInterface.getInstance();
    private boolean isTrue = true;

    public ControllerMain(AddressDAO addressDAO,
                          CustomerDAO customerDAO,
                          AccountDAO accountDAO,
                          PaymentTransactionDAO paymentTransactionDAO,
                          CurrencyApi currencyApi) {

        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.paymentTransactionDAO = paymentTransactionDAO;
        this.currencyApi = currencyApi;

        this.controllerCreateAccount = new ControllerCreateAccount(this.in,
                this.addressDAO,
                this.customerDAO,
                this.accountDAO);

        this.controllerLogin = new ControllerLogin(this.in,
                this.addressDAO,
                this.customerDAO,
                this.accountDAO,
                this.paymentTransactionDAO);
    }

    public void displayMainMenu() throws IOException {
        while (isTrue) {
            String choice = null;
            System.out.println("What do you want to do?");
            toolbar();
            choice = in.next();
            switch (choice) {
                case "l":
                    System.out.println("login");
                    controllerLogin.loginMenu(currencyApi);
                    break;
                case "c":
                    System.out.println("create account");
                    controllerCreateAccount.createAccountMenu();
                    break;
                case "x": {
                    isTrue = false;
                    break;
                }

                default:
                    System.out.println("incorrect input, try again");

                    break;
            }
        }
    }

    private void toolbar() {
        System.out.println(
                "[l-login][c-create account][x-exit]"
        );
    }
}
