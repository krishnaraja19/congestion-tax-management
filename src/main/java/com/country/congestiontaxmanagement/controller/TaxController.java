package com.country.congestiontaxmanagement.controller;

import com.country.congestiontaxmanagement.dto.CongestionTax;
import com.country.congestiontaxmanagement.exception.CustomException;
import com.country.congestiontaxmanagement.exception.ResourceNotFoundException;
import com.country.congestiontaxmanagement.exception.TaxCalculationError;
import com.country.congestiontaxmanagement.model.City;
import com.country.congestiontaxmanagement.model.Vehicle;
import com.country.congestiontaxmanagement.repository.CityRepository;
import com.country.congestiontaxmanagement.service.TaxCalculation;
import com.country.congestiontaxmanagement.service.TollStreamingImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/1.0")
public class TaxController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaxCalculation taxCalculation;

    @Autowired
    TollStreamingImplementation tollStreamingImplementation;

    @Autowired
    CityRepository cityRepository;
    //
    @GetMapping("/vehicle/{city}/{id}")
    public ResponseEntity<CongestionTax> getTaxInvoice(@PathVariable("city") String city,@PathVariable("id") long vehicleNumber){
        try {
            Optional<City> city_ = cityRepository.findByCityName(city);
            if (city_.isEmpty())
                throw new ResourceNotFoundException("The " + city + " city is not configured under this API");

            Optional<Vehicle> vehicle = tollStreamingImplementation.findByVehicleNumber(vehicleNumber);
            if (vehicle.isPresent()) {
                Optional<CongestionTax> congestionTax = taxCalculation.calculateTaxForSingleVehicle(vehicle, city);
                if (congestionTax.isPresent())
                    return new ResponseEntity(congestionTax.get(), HttpStatus.OK);
                else
                    throw new TaxCalculationError("Tax calculation error, Please contact support");
            } else
                throw new ResourceNotFoundException("No entry or exit via toll for this vehicle '" + vehicleNumber + "'");
        }catch(Exception ex){
            throw new CustomException(ex.getLocalizedMessage());
        }
    }

}
