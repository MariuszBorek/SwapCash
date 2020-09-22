package pl.exchangeapp.controller;

import pl.exchangeapp.dao.*;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import pl.exchangeapp.entities.PaymentTransaction;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SampleCustomer {
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private pl.exchangeapp.dao.PaymentTransactionDAO paymentTransactionDAO;

    public SampleCustomer(AddressDAO addressDAO,
                          CustomerDAO customerDAO,
                          AccountDAO accountDAO,
                          pl.exchangeapp.dao.PaymentTransactionDAO paymentTransactionDAO) {
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.paymentTransactionDAO = paymentTransactionDAO;
    }

    public void createSampleCustomers() {
        Address address1 = new Address()
                .withStreet("Błotna")
                .withNumber("5b")
                .withCity("Warszawa")
                .withPostalCode("33-456")
                .build();

        Address address2 = new Address()
                .withStreet("Wojska Polskiego")
                .withNumber("43")
                .withCity("Kraków")
                .withPostalCode("36-547")
                .build();

        Account account1 = new Account()
                .withTypeOfAccount(Currency.PLN)
                .withBalance(new BigDecimal(10_000))
                .build();

        Account account2 = new Account()
                .withTypeOfAccount(Currency.EUR)
                .withBalance(new BigDecimal(20_000))
                .build();

        Account account3 = new Account()
                .withTypeOfAccount(Currency.USD)
                .withBalance(new BigDecimal(15_000))
                .build();

        Account account4 = new Account()
                .withTypeOfAccount(Currency.JPY)
                .withBalance(new BigDecimal(15_000))
                .build();

                PaymentTransaction transaction1 = new PaymentTransaction()
                .withAccount(account1)
                .withTransactionType(TransactionType.INCOMING)
                .withDate(LocalDate.now())
                .withAmount(new BigDecimal(543))
                .build();

        PaymentTransaction transaction2 = new PaymentTransaction()
                .withAccount(account2)
                .withTransactionType(TransactionType.OUTGOING)
                .withDate(LocalDate.of(2020, 5, 23))
                .withAmount(new BigDecimal(234))
                .build();

        Customer customer1 = new Customer()
                .withPhoneNumber(555_777_555)
                .withFirstName("Wacław")
                .withLastName("Kowalski")
                .withPassword("zaqwsx")
                .withAccounts(List.of(account1, account3, account4))
                .withAddress(address1)
                .build();

        Customer customer2 = new Customer()
                .withPhoneNumber(658_345_964)
                .withFirstName("Justyna")
                .withLastName("Nowak")
                .withPassword("qwerty")
                .withAccounts(List.of(account2))
                .withAddress(address2)
                .build();

        addressDAO.createAddress(address1);
        addressDAO.createAddress(address2);

        accountDAO.createAccount(account1);
        accountDAO.createAccount(account2);
        accountDAO.createAccount(account3);
        accountDAO.createAccount(account4);

        paymentTransactionDAO.createCustomerTransaction(transaction1);
        paymentTransactionDAO.createCustomerTransaction(transaction2);

        customerDAO.createCustomer(customer1);
        customerDAO.createCustomer(customer2);
    }
}
