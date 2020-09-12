package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"customers", "transactions"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountNumber;
    private BigDecimal balance;
    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;
    @OneToMany(mappedBy = "account")
    private List<CustomerTransaction> customerTransactions;
}
