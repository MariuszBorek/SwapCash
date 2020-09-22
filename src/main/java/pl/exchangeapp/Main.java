package pl.exchangeapp;

import pl.exchangeapp.api.CurrencyApi;
import pl.exchangeapp.conection.ClientHttp;
import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.controller.SampleCustomer;
import pl.exchangeapp.controller.GraphicalInterface;
import pl.exchangeapp.controller.ControllerMain;
import pl.exchangeapp.converters.JsonDataConverter;
import pl.exchangeapp.dao.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // create connection with Database
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // create instances of repositories
        AddressDAO addressDAO = new AddressRepository(databaseConnection);
        CustomerDAO customerDAO = new CustomerRepository(databaseConnection);
        AccountDAO accountDAO = new AccountRepository(databaseConnection);
        PaymentTransactionDAO paymentTransactionDAO = new PaymentTransactionRepository(databaseConnection);
        CurrencyApi currencyApi = new CurrencyApi(ClientHttp.getInstance(), new JsonDataConverter());

        // sample customers
        SampleCustomer sampleCustomer = new SampleCustomer(addressDAO,
                customerDAO,
                accountDAO,
                paymentTransactionDAO
        );
        sampleCustomer.createSampleCustomers();

        // Controller
        GraphicalInterface graphicalInterface = GraphicalInterface.getInstance();
        ControllerMain controllerMain = new ControllerMain(addressDAO,
                customerDAO,
                accountDAO,
                paymentTransactionDAO,
                currencyApi);
        controllerMain.displayMainMenu();

    }
}
