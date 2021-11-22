package com.country.congestiontaxmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tax_rule")
public class TaxRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_rule_id")
    private Long id;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime fromTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime toTime;
    private Long price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "city_id"), name = "city_id")
    @JsonIgnore
    private City city;

}
