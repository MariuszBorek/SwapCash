package pl.exchangeapp.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private LocalDate date;
    @ManyToOne
    private Account account;

    public PaymentTransaction() {
    }

    public PaymentTransaction(BigDecimal amount, LocalDate date, Account account) {
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public PaymentTransaction withAmount(BigDecimal amount) {
        this.amount = amount;
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

    public PaymentTransaction build() {
        return new PaymentTransaction(amount, date, account);
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTransaction that = (PaymentTransaction) o;
        return id == that.id &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
