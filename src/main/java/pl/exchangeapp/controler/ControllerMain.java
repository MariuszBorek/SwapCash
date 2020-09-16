package pl.exchangeapp.controler;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.dao.*;

import java.util.Scanner;

public class ControllerMain implements Controller {
    private ScannerProvider scannerProvider = ScannerProvider.getInstance();
    private Scanner in = scannerProvider.getScanner();

    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private PaymentTransactionDAO paymentTransactionDAO;
    private ControllerCreateAccount controllerCreateAccount;
    private ControllerLogin controllerLogin;
    private boolean isTrue = true;

    public ControllerMain(AddressDAO addressDAO,
                          CustomerDAO customerDAO,
                          AccountDAO accountDAO,
                          PaymentTransactionDAO paymentTransactionDAO) {

        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.paymentTransactionDAO = paymentTransactionDAO;

        this.controllerCreateAccount = new ControllerCreateAccount(in,
                this.addressDAO,
                this.customerDAO,
                this.accountDAO
        );
        this.controllerLogin = new ControllerLogin(in,
                this.addressDAO,
                this.customerDAO,
                this.accountDAO
        );
    }

    public void displayMainMenu() {
        while (isTrue) {
            String choice = null;
            System.out.println("What do you want to do?");
            toolbar();
            choice = in.next();
            switch (choice) {
                case "l":
                    System.out.println("login");
                    controllerLogin.loginMenu();
                    displayMainMenu();
                    break;
                case "c":
                    System.out.println("create account");
                    controllerCreateAccount.createAccountMenu();
                    displayMainMenu();
                    break;
                case "x": {
                    isTrue = false;
                    break;
                }

                default:
                    System.out.println("incorrect input, try again");
                    displayMainMenu();
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
