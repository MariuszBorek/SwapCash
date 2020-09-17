package pl.exchangeapp.controller;


import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.dao.AccountDAO;
import pl.exchangeapp.dao.AddressDAO;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.dao.PaymentTransactionDAO;
import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ControllerCustomerDashboard {
    private Scanner in;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private PaymentTransactionDAO paymentTransactionDAO;
    private GraphicalInterface graphicalInterface = GraphicalInterface.getInstance();
    private boolean isTrue = true;

    public ControllerCustomerDashboard(Scanner in,
                                       AddressDAO addressDAO,
                                       CustomerDAO customerDAO,
                                       AccountDAO accountDAO,
                                       PaymentTransactionDAO paymentTransactionDAO) {
        this.in = in;
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.paymentTransactionDAO = paymentTransactionDAO;

    }

    public void getDashboard(Customer customer, CurrencyApi currencyApi) throws IOException {
        String choice = null;
        System.out.println("you are in dashboard");
        while (isTrue) {
            displayDashBoardMenu(customer);
            choice = in.next();
            switch (choice) {
                case "s":
                    displayActualExchangeRates(currencyApi);
                    break;
                case "t":
                    System.out.println("input amount to transfer");
                    BigDecimal amount = in.nextBigDecimal();

                    System.out.println("input recipient phone number");
                    int recipientPhoneNumber = in.nextInt();
                    accountDAO.transferMoney(amount,
                            customer.getPhoneNumber(),
                            recipientPhoneNumber);
                    System.out.printf("your balance: %f\n", accountDAO.getBalance(customer));
                    break;
                case "e":
                    break;
                case "h":
                    displayPaymentTransactions(customer);
                    break;
                case "u":
                    break;
                case "f":
                    break;
                case "sa":
                    break;
                case "a":
                    break;
                case "x":
                    isTrue = false;
                    break;
                case "d":
                    System.out.println("are you sure you want to delete your profile?\n" +
                            "[y-yes][n-no]");
                    String yesOrNoChoice = in.next();
                    if (yesOrNoChoice.equals("y")) {
                        customerDAO.deleteCustomerWithAllInfoAboutThem(customer.getPhoneNumber());
                    }
                    isTrue = false;
                    break;
                default:
                    System.out.println("incorrect input");

            }
        }
    }

    private void displayPaymentTransactions(Customer customer) {
        List<PaymentTransaction> transactionsForCustomer = paymentTransactionDAO.getTransactionsForCustomer(customer);
        graphicalInterface.drawLine(66);
        System.out.printf("history transactions, type: %s, account nr: %d\n",
                transactionsForCustomer.get(0).getTransactionType().name(),
                transactionsForCustomer.get(0).getAccount().getAccountNumber());
        graphicalInterface.drawLine(66);
        for (PaymentTransaction pt : transactionsForCustomer) {
            System.out.printf("|account nr: %d |2 amount: %.2f | date: %s\n",
                    pt.getAccount().getAccountNumber(),
                    pt.getTransactionAmount(),
                    pt.getDate());
        }
        graphicalInterface.drawLine(66);
        System.out.println("[x-go back to dashboard]");
        String goBackToDashboard = in.next();
    }

    private void displayDashBoardMenu(Customer customer) {
        graphicalInterface.drawLine(66);
        System.out.printf(
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<__DASHBOARD__>>>>>>>>>>>>>>>>>>>>>>>>>>\n" +
                        "[s-show actual exchange rates][t-transfer money][e-exchange money]\n" +
                        "[h-transaction history][u-update your data][f-save trans. history]\n" +
                        "[sa-show accounts][a-add foreign currency account][x-logout]\n" +
                        "[d-delete profile]\n" +
                        "LOGIN AS: %s %s | BALANCE: %12.2f | ACCOUNT TYPE: %s\n",
                customer.getFirstName(),
                customer.getLastName(),
                accountDAO.getBalance(customer),
                customer.getAccounts().get(0).getTypeOfAccount().name());
        graphicalInterface.drawLine(66);
    }

    private void displayActualExchangeRates(CurrencyApi currencyApi) throws IOException {
        List<CurrencyInfo> listOfActualExchangeRates = currencyApi.getListOfActualExchangeRates();
        System.out.printf("Date of exchange rates: %s\n", listOfActualExchangeRates.get(0).getDate().toString());
        graphicalInterface.drawLine(66);
        for (CurrencyInfo currencyInfo : listOfActualExchangeRates) {
            System.out.printf("|%28s | buy: %10.4f | sell: %10.4f\n",
                    currencyInfo.getName(),
                    currencyInfo.getBuy(),
                    currencyInfo.getSell());
            graphicalInterface.drawLine(66);
        }

    }

}
