package com.library.models.dto;

import com.library.models.Fee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int id;
    private int userId;
    private double amount;
    private ArrayList<Fee> feesPaid;
}
