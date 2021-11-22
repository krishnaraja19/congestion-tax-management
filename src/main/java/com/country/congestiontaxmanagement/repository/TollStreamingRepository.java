package com.country.congestiontaxmanagement.repository;

import com.country.congestiontaxmanagement.model.TollStreaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TollStreamingRepository extends JpaRepository<TollStreaming,Long> {

        @Query(value = "select t.* from toll_streaming t,vehicle v where t.vehicle_id = v.vehicle_id and v.vehicle_number = :id order by t.in_or_out_time asc", nativeQuery = true)
        List<TollStreaming> findVehicleByVehicleNumber(Long id);
}
