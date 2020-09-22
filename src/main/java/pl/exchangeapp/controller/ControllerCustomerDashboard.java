package pl.exchangeapp.controller;


import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.dao.AccountDAO;
import pl.exchangeapp.dao.AddressDAO;
import pl.exchangeapp.dao.CustomerDAO;
import pl.exchangeapp.dao.PaymentTransactionDAO;
import pl.exchangeapp.domainnbp.CurrencyInfo;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.outputfiles.FileOperations;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
                    transferMoney(customer);
                    break;
                case "e":
                    exchangeMoney(customer, currencyApi);
                    break;
                case "h":
                    displayPaymentTransactions(customer);
                    break;
                case "u":
                    changeCustomerData(customer);
                    break;
                case "f":
                    saveTransactionHistoryIntoFile(customer);
                    break;
                case "sa":
                    showAccounts(customer);
                    break;
                case "a":
                    addForeignAccount(customer);
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

    private void transferMoney(Customer customer) {
        System.out.println("input amount to transfer");
        BigDecimal amount = in.nextBigDecimal();

        System.out.println("input recipient phone number");
        int recipientPhoneNumber = in.nextInt();
        accountDAO.transferMoney(amount,
                customer.getPhoneNumber(),
                recipientPhoneNumber);
        System.out.printf("your balance: %f\n", accountDAO.getBalance(customer));
    }

    private void displayPaymentTransactions(Customer customer) {
        List<PaymentTransaction> transactionsForCustomer = paymentTransactionDAO.getTransactionsForCustomer(customer);
        if(transactionsForCustomer == null) {
            System.out.println("You do not have any transaction yet");
        } else {
            graphicalInterface.drawLine(66);
            System.out.printf("history transactions, type: %s, account nr: %d\n",
                    transactionsForCustomer.get(0).getTransactionType().name(),
                    transactionsForCustomer.get(0).getAccount().getAccountNumber());
            graphicalInterface.drawLine(66);
            for (PaymentTransaction pt : transactionsForCustomer) {
                System.out.printf("|account nr: %d | amount: %.2f | date: %s\n",
                        pt.getAccount().getAccountNumber(),
                        pt.getTransactionAmount(),
                        pt.getDate());
            }
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
                        "LOGIN AS: %s %s | BALANCE: %.2f | ACCOUNT TYPE: %s\n",
                customerDAO.getName(customer),
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
        System.out.println("[x-go back to dashboard]");
        String goBackToDashboard = in.next();

    }

    public void showAccounts(Customer customer) {
        List<Account> accounts = customerDAO.getCustomerAccounts(customer.getPhoneNumber());
        graphicalInterface.drawLine(66);
        for (Account account : accounts) {
            System.out.printf("Currency: %s | account no: %d | balance: %.2f\n",
                    account.getTypeOfAccount(),
                    account.getAccountNumber(),
                    account.getBalance());
        }
        graphicalInterface.drawLine(66);
        System.out.println("[press any letter and enter to go]");
        String goBackToDashboard = in.next();
    }

    public void addForeignAccount(Customer customer) {
        System.out.println("enter your currency");
        for (Currency currency : Currency.values()) {
            System.out.print(currency.name() + "; ");
        }
        System.out.println();
        String typeOfCurrency = in.next().toUpperCase();
        Currency currency = Currency.valueOf(typeOfCurrency);

        Account account = new Account()
                .withTypeOfAccount(currency)
                .withBalance(new BigDecimal(10_000))
                .build();
        accountDAO.createAccount(account);
        customerDAO.addAccount(customer.getPhoneNumber(), account);
    }

    public void exchangeMoney(Customer customer, CurrencyApi currencyApi) throws IOException {
        List<Account> accounts = customerDAO.getCustomerAccounts(customer.getPhoneNumber());
        System.out.printf("list of available accounts for: %s %s\n",
                customer.getFirstName(),
                customer.getLastName());
        showAccounts(customer);
        graphicalInterface.drawLine(66);
        System.out.println("select from witch account?");
        System.out.println("enter account no:");
        long accountNumberFromGetMoney = in.nextLong();
        System.out.println("select to witch account?");
        System.out.println("enter account no:");
        long accountNumberToPutMoney = in.nextLong();
        System.out.println("enter how much money would you like to transfer");
        BigDecimal amount = in.nextBigDecimal();

        Account accountFromGetMoney = null;
        Account accountToPutMoney = null;
        for(Account account : accounts) {
            if(account.getAccountNumber() == accountNumberFromGetMoney) {
                accountFromGetMoney = account;
            }
        }
        for(Account account : accounts) {
            if(account.getAccountNumber() == accountNumberToPutMoney) {
                accountToPutMoney = account;
            }
        }
        if(accountFromGetMoney == null || accountToPutMoney == null) {
            System.out.println("you have you do not have account with this number");
            return;
        }

        CurrencyInfo currencyFromAccountGettingMoney = currencyApi.getActualExchangeRateForChosenCurrency(accountFromGetMoney.getTypeOfAccount());
        CurrencyInfo currencyFromAccountPuttingMoney = currencyApi.getActualExchangeRateForChosenCurrency(accountToPutMoney.getTypeOfAccount());

        BigDecimal exchangedToPLN = amount.multiply(new BigDecimal(currencyFromAccountGettingMoney.getBuy().toString()));
        System.out.println(exchangedToPLN);

        BigDecimal exchangedFromPLNToDiffCurrency = exchangedToPLN.divide(new BigDecimal(currencyFromAccountPuttingMoney.getSell().toString()), RoundingMode.DOWN);
        System.out.println(exchangedFromPLNToDiffCurrency);

        accountDAO.exchangeMoney(accountFromGetMoney, accountToPutMoney, amount, exchangedFromPLNToDiffCurrency);

        graphicalInterface.drawLine(66);
        System.out.println("[x-go back to dashboard]");
        String goBackToDashboard = in.next();

    }

    private void changeCustomerData(Customer customer) {
        System.out.println("[n-change name][p-change password]");
        String choiceUpdateData = in.next();
        if(choiceUpdateData.equals("n")) {
            System.out.println("enter your name");
            String name = in.next();
            changeCustomerName(customer, name);
        } else if(choiceUpdateData.equals("p")) {
            System.out.println("enter your new password");
            String password = in.next();
            changeCustomerPassword(customer, password);
        } else {
            System.out.println("illegal sign, try again");
            changeCustomerData(customer);
        }
    }

    private void changeCustomerName(Customer customer, String name) {
        customerDAO.changeName(customer, name);
    }

    private void changeCustomerPassword(Customer customer, String password) {
        customerDAO.changePassword(customer, password);
    }

    private void saveTransactionHistoryIntoFile(Customer customer) {
        String transactions = paymentTransactionDAO.getTransactionsForCustomer(customer).toString();
        FileOperations fileOperations = new FileOperations();
        fileOperations.saveTransactionHistoryToFile(transactions);
    }

}
