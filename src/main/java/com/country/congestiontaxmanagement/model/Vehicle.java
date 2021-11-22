package com.country.congestiontaxmanagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    private Long vehicleNumber;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(foreignKey = @ForeignKey(name = "vehicle_type_id"),name = "vehicle_type_id",
            nullable = false, updatable = false)
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle", cascade = {
            CascadeType.MERGE
    })
    @JsonIgnore
    private List< TollStreaming > tollStreamingList;
}
