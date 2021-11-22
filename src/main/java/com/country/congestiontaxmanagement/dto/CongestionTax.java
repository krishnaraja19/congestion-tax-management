package com.country.congestiontaxmanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class CongestionTax {
    private Long vehicleNumber;
    private Long totalTaxAmount;
    private List<EntryDetails> entryAndExitDetailsList;

}
