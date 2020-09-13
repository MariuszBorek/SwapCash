package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DataBaseConnection;
import pl.exchangeapp.entities.Address;

public class AddressRepository implements AddressDAO {
    private DataBaseConnection dataBaseConnection;

    public AddressRepository(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createAddress(Address address) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(address));
    }
}
