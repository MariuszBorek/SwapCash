package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;

import java.util.List;

public class CustomerRepository implements CustomerDAO {
    private DatabaseConnection dataBaseConnection;

    public CustomerRepository(DatabaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Deprecated
    @Override
    public void createCustomer(Customer customer) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(customer));
    }

    @Deprecated
    @Override
    public void updateCustomer(Customer customer, int phoneNumber) {
        dataBaseConnection.myQueryConsumer(session -> {
            Customer foundCustomer = session.find(Customer.class, phoneNumber);
            foundCustomer.setFirstName(customer.getFirstName());
            foundCustomer.setLastName(customer.getLastName());
            foundCustomer.setPassword(customer.getPassword());
            session.update(foundCustomer);
            System.out.println(foundCustomer);
        });
    }

    @Override
    public void updateCustomer(Customer customer) {
        dataBaseConnection.myQueryConsumer(session -> session.update(customer));
    }

    @Override
    public void deleteCustomerWithAllInfoAboutThem(int phoneNumber) {
        AddressRepository addressRepository = new AddressRepository(dataBaseConnection);
        dataBaseConnection.myQueryConsumer(session -> {
            Customer foundCustomer = session.find(Customer.class, phoneNumber);
            List<Account> accounts = foundCustomer.getAccounts();
            List<PaymentTransaction> paymentTransactions = accounts.get(0).getPaymentTransactions();
            Address address = foundCustomer.getAddress();

            // TODO check it if work correctly
            for (int i = 0; i < accounts.size(); i++) {
                for (int j = 0; j < accounts.get(i).getPaymentTransactions().size(); j++) {
                    session.delete(paymentTransactions.get(j));
                }
            }
            session.delete(foundCustomer);
            session.delete(address);
            for (Account account : accounts) {
                session.delete(account);
            }

//            for (PaymentTransaction paymentTransaction : paymentTransactions) {
//                session.delete(paymentTransaction);
//            }

        });
    }

    @Override
    public void findByPhoneNumber(int phoneNumber) {
        dataBaseConnection.myQueryConsumer(session -> {
            Customer customer = session.find(Customer.class, phoneNumber);
            System.out.println(customer);
        });
    }

    @Deprecated
    public void findByName(String firstName) {
        dataBaseConnection.myQueryConsumer(session -> {
            Customer foundCustomer = session.createQuery("from Customer where firstName = :firstName", Customer.class)
                    .setParameter("firstName", firstName)
                    .getSingleResult();
            System.out.println("findByName-->>" + foundCustomer);
        });
    }
}


