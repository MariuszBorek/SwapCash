package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.entities.Address;

public class AddressRepository implements AddressDAO {
    private DatabaseConnection dataBaseConnection;

    public AddressRepository(DatabaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createAddress(Address address) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(address));
    }

    @Override
    public void deleteAddress(Address address) {
        dataBaseConnection.myQueryConsumer(session -> session.delete(address));
    }

}
