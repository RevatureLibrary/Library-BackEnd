package com.library.models.request;

import com.library.models.Fee;
import com.library.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private User user;
    private double amount;
    private Set<Fee> feesPaid;
}
