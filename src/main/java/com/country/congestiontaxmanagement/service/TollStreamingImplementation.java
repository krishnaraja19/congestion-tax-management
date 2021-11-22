package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.TollStreaming;
import com.country.congestiontaxmanagement.model.Vehicle;
import com.country.congestiontaxmanagement.repository.TollStreamingRepository;
import com.country.congestiontaxmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TollStreamingImplementation {
    @Autowired
    TollStreamingRepository tollStreamingRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;


    public Optional<Vehicle> findByVehicleNumber(Long vehicleNumber){
        return vehicleRepository.findByVehicleNumber(vehicleNumber);
    }

    public List<TollStreaming> findVehicleData(Long vehicleNumber){
        return tollStreamingRepository.findVehicleByVehicleNumber(vehicleNumber);
    }

}
