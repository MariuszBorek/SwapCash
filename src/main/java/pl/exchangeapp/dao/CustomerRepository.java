package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DataBaseConnection;
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
}
