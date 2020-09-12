package pl.exchangeapp.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude =  "account")
public class CustomerTransaction {
    @Id
    @GeneratedValue
    private UUID id;
    private BigDecimal amount;
    private Date date;
    @ManyToOne
    private Account account;
}
