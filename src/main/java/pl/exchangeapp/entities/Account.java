package pl.exchangeapp.entities;

import pl.exchangeapp.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;
    private Currency typeOfAccount;
    private BigDecimal balance;
    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;
    @OneToMany(mappedBy = "account")
    private List<PaymentTransaction> paymentTransactions;

    public Account() {
    }

    public Account(long accountNumber, BigDecimal balance, Currency typeOfAccount) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.typeOfAccount = typeOfAccount;
    }

    public Account withAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Account withTypeOfAccount(Currency typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
        return this;
    }

    public Account withBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(accountNumber, balance, typeOfAccount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", typeOfAccount=" + typeOfAccount +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber &&
                typeOfAccount == account.typeOfAccount &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, typeOfAccount, balance);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(Currency typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }
}
