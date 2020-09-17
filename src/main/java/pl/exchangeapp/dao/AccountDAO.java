package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;

import java.math.BigDecimal;

public interface AccountDAO {
    void createAccount(Account account);
    void transferMoney(BigDecimal amount, int payerPhoneNumber, int recipientPhoneNumber);
    BigDecimal getBalance(Customer customer);
}
