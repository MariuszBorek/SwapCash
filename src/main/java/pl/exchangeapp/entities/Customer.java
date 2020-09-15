package pl.exchangeapp.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    private int phoneNumber;
    private String password;
    private String lastName;
    private String firstName;
    @OneToOne
    private Address address;
    @ManyToMany
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(int phoneNumber,
                    String password,
                    String firstName,
                    String lastName,
                    Address address,
                    List<Account> accounts) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
        this.address = address;
    }

    public Customer withPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Customer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer withPassword(String password) {
        this.password = password;
        return this;
    }

    public Customer withAddress(Address address) {
        this.address = address;
        return this;
    }

    public Customer withAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public Customer build() {
        return new Customer(phoneNumber,
                password,
                firstName,
                lastName,
                address,
                accounts);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phoneNumber == customer.phoneNumber &&
                Objects.equals(password, customer.password) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(firstName, customer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, password, lastName, firstName);
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
