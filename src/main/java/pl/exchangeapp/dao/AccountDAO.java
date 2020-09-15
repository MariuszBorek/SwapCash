package pl.exchangeapp.dao;

import pl.exchangeapp.entities.Account;

import java.math.BigDecimal;

public interface AccountDAO {
    void createAccount(Account account);
    void transferMoney(BigDecimal amount, int payerPhoneNumber, int phoneNumber);
}
