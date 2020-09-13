package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"address", "accounts"})
@ToString
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    @OneToOne
    private Address address;
    @ManyToMany
    @Column(name = "accountNumber_id")
    private List<Account> accounts;

    public Customer(String firstName,
                    String lastName,
                    String password,
                    List<Account> accounts,
                    Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accounts = accounts;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public Customer setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Customer setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public Customer build() {
        return new Customer(firstName,
                lastName,
                password,
                accounts,
                address);
    }
}
