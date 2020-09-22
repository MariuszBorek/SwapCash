package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;

import java.util.List;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer, int phoneNumber);
    void updateCustomer(Customer customer);
    void findByName(String firstName);
    void deleteCustomerWithAllInfoAboutThem(int phoneNumber);
    Customer findByPhoneNumber(int phoneNumber);
    void addAccount(int phoneNumber, Account account);
    List<Account>  getCustomerAccounts(int phoneNumber);
    void changeName(Customer customer, String name);
    void changePassword(Customer customer, String password);
    String getName(Customer customer);

}
