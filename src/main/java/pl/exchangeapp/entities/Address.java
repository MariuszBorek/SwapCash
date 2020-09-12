package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "customer")
public class Address {
    @Id
    private UUID id;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    @OneToOne
    private Customer customer;
}
