package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DataBaseConnection;
import pl.exchangeapp.entities.Account;

public class AccountRepository implements AccountDAO {
    private DataBaseConnection dataBaseConnection;

    public AccountRepository(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createAccount(Account account) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(account));
    }
}
