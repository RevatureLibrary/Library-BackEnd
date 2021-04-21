package com.library.models.dto;

import com.library.models.enums;
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

    private enums.FeeStatus feeStatus;

    private enums.FeeType feeType;
}
