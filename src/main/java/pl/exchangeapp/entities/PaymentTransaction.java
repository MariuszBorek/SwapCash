package pl.exchangeapp.entities;

import pl.exchangeapp.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal transactionAmount;
    private TransactionType transactionType;
    private LocalDate date;
    @ManyToOne
    private Account account;

    public PaymentTransaction() {
    }

    public PaymentTransaction(BigDecimal transactionAmount, TransactionType transactionType, LocalDate date, Account account) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.date = date;
        this.account = account;
    }

    public PaymentTransaction withAmount(BigDecimal amount) {
        this.transactionAmount = amount;
        return this;
    }

    public PaymentTransaction withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public PaymentTransaction withAccount(Account account) {
        this.account = account;
        return this;
    }

    public PaymentTransaction withTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public PaymentTransaction build() {
        return new PaymentTransaction(transactionAmount, transactionType, date, account);
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "id=" + id +
                ", amount=" + transactionAmount +
                ", transactionType=" + transactionType +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTransaction that = (PaymentTransaction) o;
        return id == that.id &&
                Objects.equals(transactionAmount, that.transactionAmount) &&
                transactionType == that.transactionType &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionAmount, transactionType, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
