package pl.exchangeapp.dao;

import pl.exchangeapp.entities.PaymentTransaction;

public interface PaymentTransactionDAO {
    void createCustomerTransaction(PaymentTransaction paymentTransaction);
}
