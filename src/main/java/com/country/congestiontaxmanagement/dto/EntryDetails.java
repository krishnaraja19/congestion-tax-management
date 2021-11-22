package com.country.congestiontaxmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntryDetails {
    private int entryNo;
    private LocalDateTime entryOrExitTime;
    private Long amount;
}
