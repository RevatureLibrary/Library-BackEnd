package com.library.models;
import com.library.models.dto.CheckoutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// This @Data annotation provided by lombok automatically generates
// the getters, setters, toString, and equals method for us at runtime
@Data
@AllArgsConstructor
@Entity(name="Checkout")
public class Checkout {
    @Id
    @GeneratedValue
    private int id;
    // The transient keyword makes it so that Hibernate won't persist the now field
    // as a column in the database
    private transient LocalDateTime now;
    private Timestamp checkoutDate;
    private Timestamp returnDueDate;
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne(cascade={CascadeType.ALL})
    private Fee fee;
    @Enumerated
    private enums.CheckoutStatus checkoutStatus;

    public Checkout(){
        now = LocalDateTime.now();
        returnDueDate = Timestamp.valueOf(now.plusDays(14));
        checkoutDate = Timestamp.valueOf(now);
        checkoutStatus = enums.CheckoutStatus.DUE;
    }

    public Checkout(CheckoutDTO checkoutDTO){
        now = LocalDateTime.now();
        checkoutDate = Timestamp.valueOf(now);
        returnDueDate = Timestamp.valueOf(now.plusDays(14));
        checkoutStatus = enums.CheckoutStatus.DUE;

    }
}
