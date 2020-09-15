package pl.exchangeapp.dao;

import pl.exchangeapp.conection.DatabaseConnection;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Customer;

import java.math.BigDecimal;

public class AccountRepository implements AccountDAO {
    private DatabaseConnection dataBaseConnection;

    public AccountRepository(DatabaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void createAccount(Account account) {
        dataBaseConnection.myQueryConsumer(session -> session.persist(account));
    }

    @Override
    public void transferMoney(BigDecimal amount, int payerPhoneNumber, int recipientPhoneNumber) {
        dataBaseConnection.myQueryConsumer(session -> {
            Customer foundPayer = session.createQuery("from Customer where phoneNumber = :phoneNumber", Customer.class)
                    .setParameter("phoneNumber", payerPhoneNumber)
                    .getSingleResult();

            Customer foundRecipient = session.createQuery("from Customer where phoneNumber = :phoneNumber", Customer.class)
                    .setParameter("phoneNumber", recipientPhoneNumber)
                    .getSingleResult();

            System.out.println("Payer -->" + foundPayer);
            System.out.println("Recipient -->" + foundRecipient);

            BigDecimal payerBalance = foundPayer.getAccounts().get(0).getBalance();
            BigDecimal recipientBalance = foundRecipient.getAccounts().get(0).getBalance();

            // TODO add type of account, you cannot transfer money form USD to PLN
            foundPayer.getAccounts().get(0).setBalance(payerBalance.subtract(amount));
            foundRecipient.getAccounts().get(0).setBalance(recipientBalance.add(amount));

            session.update(foundPayer);
            session.update(foundRecipient);

            // TODO add transaction history



        });
    }
}
