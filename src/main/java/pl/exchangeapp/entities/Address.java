package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;


@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "customer")
public class Address {
    @Id
    @GeneratedValue()
    private UUID id;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Address(String street,
                   String number,
                   String postalCode,
                   String city) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Address setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address build() {
        return new Address(street,
                number,
                postalCode,
                city);
    }
}
