package com.country.congestiontaxmanagement.dto;

import com.country.congestiontaxmanagement.model.City;
import com.country.congestiontaxmanagement.model.Vehicle;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RawStreamingData {
    private Long id;
    private Long vehicleId;
    private LocalDateTime inOrOutTime;
    private Long price;

    private City city;

    private Vehicle vehicle;
}
