package com.country.congestiontaxmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "toll_streaming")
public class TollStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toll_streaming_id")
    private Long id;

    private LocalDateTime InOrOutTime;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "entry_city_id"), name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE )
    @JoinColumn(foreignKey = @ForeignKey(name = "entry_vehicle_id"), name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;

}
