package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer, Account account);
    void deleteCustomer(Customer customer);
    void findByAccountNumber(Customer customer);

}
