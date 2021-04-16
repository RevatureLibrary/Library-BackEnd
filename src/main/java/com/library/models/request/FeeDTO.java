package com.library.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDTO {
    private Timestamp assessed;

    private Timestamp resolved;

    private double amount;
}
