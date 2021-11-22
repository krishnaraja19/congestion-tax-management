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
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    private String cityName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "city", cascade = {
            CascadeType.ALL
    })
    @JsonIgnore
    private List< TaxRule > taxRuleList;

    @OneToMany(mappedBy = "city", cascade = {
            CascadeType.ALL
    })
    @JsonIgnore
    private List< TollStreaming > tollStreamingList;


}
