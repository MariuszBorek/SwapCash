package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DataBaseConnection;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;

public class CustomerRepository implements CustomerDAO {
    private DataBaseConnection dataBaseConnection;

    public CustomerRepository(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createCustomer(Customer customer) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(customer));
    }

    @Override
    public void updateCustomer(Customer newCustomerData, Account account) {
        // TODO
//        Customer foundCustomer = dataBaseConnection.myQueryFunction(session -> session.find(Customer.class, account.getAccountNumber()));
//        new Customer()
//                .setFirstName(newCustomerData.getFirstName())
//                .setLastName(newCustomerData.getLastName())
//                .setPassword(newCustomerData.getPassword())
//                .build();
//
//        dataBaseConnection.myQueryConsumer(session -> session.createQuery(""));
    }

    @Override
    public void deleteCustomer(Customer customer) {
        // TODO
    }

    @Override
    public void findByAccountNumber(Customer customer) {
        // TODO
    }
}
