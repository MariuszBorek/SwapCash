package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;

public interface AddressDAO {
    void createAddress(Address address);
    void deleteAddress(Address address);
}
