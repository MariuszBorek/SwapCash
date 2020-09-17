package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;

import java.util.List;

public interface PaymentTransactionDAO {
    void createCustomerTransaction(PaymentTransaction paymentTransaction);
    List<PaymentTransaction> getTransactionsForCustomer(Customer customer);
}
