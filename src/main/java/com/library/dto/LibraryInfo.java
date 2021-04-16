package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LibraryInfo {
    String LibraryName;
    Time openingTime;
    Time closingTime;
    Boolean isOpen;
}
