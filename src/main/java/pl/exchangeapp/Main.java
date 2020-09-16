package pl.exchangeapp;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.controler.SampleCustomer;
import pl.exchangeapp.controler.GraphicalInterface;
import pl.exchangeapp.controler.ControllerMain;
import pl.exchangeapp.dao.*;

public class Main {
    public static void main(String[] args) {
        // create connection with Database
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // create instances of repositories
        AddressDAO addressDAO = new AddressRepository(databaseConnection);
        CustomerDAO customerDAO = new CustomerRepository(databaseConnection);
        AccountDAO accountDAO = new AccountRepository(databaseConnection);
        PaymentTransactionDAO paymentTransactionDAO = new PaymentTransactionRepository(databaseConnection);

        // sample customers
        SampleCustomer sampleCustomer = new SampleCustomer(addressDAO,
                customerDAO,
                accountDAO,
                paymentTransactionDAO
        );
        sampleCustomer.createSampleCustomers();

        // Controller
        GraphicalInterface graphicalInterface = new GraphicalInterface();
        ControllerMain controllerMain = new ControllerMain(addressDAO,
                customerDAO,
                accountDAO,
                paymentTransactionDAO);

        graphicalInterface.drawLine();
        controllerMain.displayMainMenu();

    }
}
