package pl.exchangeapp.controller;

import jdk.swing.interop.SwingInterOpUtils;
import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.dao.*;

import java.io.IOException;
import java.util.Scanner;

public class ControllerMain {
    private ScannerProvider scannerProvider = ScannerProvider.getInstance();
    private Scanner in = scannerProvider.getScanner();
    private GraphicalInterface gf = GraphicalInterface.getInstance();

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
                    gf.drawLine(66);
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<__LOGIN__>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    gf.drawLine(66);
                    controllerLogin.loginMenu(currencyApi);
                    break;
                case "c":
                    gf.drawLine(66);
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<__CREATE__ACCOUNT__>>>>>>>>>>>>>>>>>>>>>>>");
                    gf.drawLine(66);
                    controllerCreateAccount.createAccountMenu();
                    break;
                case "x": {
                    System.out.println("see you");
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
                "You can log in as sample user(below) or create your own account\n" +
                "| phone: 658 345 964 password: qwerty |\n" +
                        "| phone: 555 777 555 password: zaqwsx |\n" +
                        "[l-login][c-create account][x-exit]"
        );
    }
}
