package pl.exchangeapp.dao;

import pl.exchangeapp.entities.PaymentTransaction;

public interface CustomerTransactionDAO {
    void createCustomerTransaction(PaymentTransaction paymentTransaction);
}
