package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"customers", "customerTransactions"})
public class Account {
    @Id
    @Column(unique = true)
    private long accountNumber;
    private BigDecimal balance;
    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;
    @OneToMany(mappedBy = "account")
    private List<CustomerTransaction> customerTransactions;

    public Account(long accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(accountNumber, balance);
    }
}
