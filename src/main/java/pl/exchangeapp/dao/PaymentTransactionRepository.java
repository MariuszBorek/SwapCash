package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;

import java.util.List;

public class PaymentTransactionRepository implements pl.exchangeapp.dao.PaymentTransactionDAO {
    private DatabaseConnection dataBaseConnection;

    public PaymentTransactionRepository(DatabaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createCustomerTransaction(PaymentTransaction paymentTransaction) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(paymentTransaction));
    }

    @Override
    public List<PaymentTransaction> getTransactionsForCustomer(Customer customer) {
        return dataBaseConnection.myQueryFunction(session ->
                session.createQuery("from PaymentTransaction where account_accountNumber = :account_accountNumber",
                PaymentTransaction.class)
                .setParameter("account_accountNumber", customer.getAccounts().get(0))
                .getResultList());
    }
}
