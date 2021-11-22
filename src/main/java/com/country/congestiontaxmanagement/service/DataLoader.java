package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.*;
import com.country.congestiontaxmanagement.repository.CityRepository;
import com.country.congestiontaxmanagement.repository.TaxRuleRepository;
import com.country.congestiontaxmanagement.repository.VehicleTypeRepository;
import com.country.congestiontaxmanagement.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataLoader {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    TaxRuleRepository taxRuleRepository;

    @Autowired
    SpecialHolidaysImplementation specialHolidaysImplementation;


    public void writeSpecialHolidays(){
        LocalDate requiredDate = LocalDate.parse("2013-02-14");
        var holiday = SpecialHoliday.builder().id(1L).holidayName("Vacation Day")
                .holidayDate(requiredDate).build();
        specialHolidaysImplementation.saveHoliday(holiday);
    }

    public void getTaxRuleForGothenburg(Resource filePath){

        List<TaxRule> taxRuleList = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(filePath.getInputStream()))) {
            taxRuleList = csvReader.lines().skip(1).map(mapToTaxRuleData).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Function<String, TaxRule> mapToTaxRuleData = (taxRuleData) -> {
        String[] taxData = taxRuleData.split(";");

        Optional<City> city = cityRepository.findByCityName(taxData[3]);
        City newCity = null;
        if(city.isEmpty()){
            newCity = City.builder()
                    .cityName(taxData[3])
                    .createdDate(Constants.getLocalDateTime())
                    .updatedDate(Constants.getLocalDateTime())
                    .build();
            cityRepository.saveAndFlush(newCity);
        }
        if(newCity == null)
            newCity = city.get();
        var taxRuleObj = TaxRule.builder()
                .fromTime(LocalTime.parse(taxData[0]))
                .toTime(LocalTime.parse(taxData[1]))
                .price(Long.parseLong(taxData[2]))
                .createdDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .city(newCity).build();
        taxRuleRepository.saveAndFlush(taxRuleObj);
        return taxRuleObj;
    };

    public void vehicleTypeLoadData(){

        List<VehicleType> taxRuleList = new ArrayList<>();
        var vehicleType0 = VehicleType.builder()
                .vehicleTypeName("Emergency vehicles")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType1 = VehicleType.builder()
                .vehicleTypeName("Busses")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType2 = VehicleType.builder()
                .vehicleTypeName("Diplomat vehicles")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType3 = VehicleType.builder()
                .vehicleTypeName("Motorcycles")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType4 = VehicleType.builder()
                .vehicleTypeName("Military vehicles")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType5 = VehicleType.builder()
                .vehicleTypeName("Foreign vehicles")
                .isTaxExemptVehicles(true)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        var vehicleType6 = VehicleType.builder()
                .vehicleTypeName("Commercial and Private")
                .isTaxExemptVehicles(false)
                .createDate(Constants.getLocalDateTime())
                .updateDate(Constants.getLocalDateTime())
                .build();
        taxRuleList.add(vehicleType0);
        taxRuleList.add(vehicleType1);
        taxRuleList.add(vehicleType2);
        taxRuleList.add(vehicleType3);
        taxRuleList.add(vehicleType4);
        taxRuleList.add(vehicleType5);
        taxRuleList.add(vehicleType6);
        vehicleTypeRepository.saveAllAndFlush(taxRuleList);
    }
}
