package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.VehicleType;
import com.country.congestiontaxmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleTypeImplementation {

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    public Optional<VehicleType> findVehicleType(Long vehicleTypeId){
        return vehicleTypeRepository.findById(vehicleTypeId);
    }
}
