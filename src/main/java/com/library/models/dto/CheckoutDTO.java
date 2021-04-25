package com.library.models.dto;

import com.library.models.Book;
import com.library.models.Fee;
import com.library.models.User;
import com.library.models.enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {
    private Timestamp checkoutDate;
    private Timestamp returnDueDate;
    private String username;
    private Integer bookId;
}
