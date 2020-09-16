package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Customer;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer, int phoneNumber);
    void updateCustomer(Customer customer);
    void findByName(String firstName);
    void deleteCustomerWithAllInfoAboutThem(int phoneNumber);
    Customer findByPhoneNumber(int phoneNumber);
}
