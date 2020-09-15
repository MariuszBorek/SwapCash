package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.entities.PaymentTransaction;

public class CustomerTransactionRepository implements CustomerTransactionDAO {
    private DatabaseConnection dataBaseConnection;

    public CustomerTransactionRepository(DatabaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createCustomerTransaction(PaymentTransaction paymentTransaction) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(paymentTransaction));
    }
}
