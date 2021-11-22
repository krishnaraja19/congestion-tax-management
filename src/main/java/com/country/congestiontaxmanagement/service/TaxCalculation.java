package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.dto.CongestionTax;
import com.country.congestiontaxmanagement.dto.EntryDetails;
import com.country.congestiontaxmanagement.model.*;
import com.country.congestiontaxmanagement.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class TaxCalculation {

    @Autowired
    TollStreamingImplementation tollStreamingImplementation;

    @Autowired
    TaxRuleImplementation taxRuleImplementation;

    @Autowired
    VehicleTypeImplementation vehicleTypeImplementation;

    @Autowired
    SpecialHolidaysImplementation specialHolidaysImplementation;

    @Autowired
    CityRepository cityRepository;

    public static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialHoliday(LocalDate entryDate){

        List<SpecialHoliday> holidayList = specialHolidaysImplementation.getSpecialHolidays();

        return holidayList.stream().anyMatch(holidays -> entryDate.equals(holidays.getHolidayDate()));
    }

    public Optional<CongestionTax> calculateTaxForSingleVehicle(Optional<Vehicle> vehicle,String city){
        List<TollStreaming> vehicleEntryList = tollStreamingImplementation.findVehicleData(vehicle.get().getVehicleNumber());
        Optional<VehicleType> vehicleType = vehicleTypeImplementation.findVehicleType(vehicle.get().getVehicleType().getId());
        Long vehicleNumber = vehicle.get().getVehicleNumber();
        CongestionTax congestionTax = new CongestionTax();
        congestionTax.setVehicleNumber(vehicleNumber);
        Optional<City> cityData = cityRepository.findByCityName(city);
        EntryDetails entryDetails = null;
        List<EntryDetails> entryDetailsList = new ArrayList<>();
        int i = 1;
        Long totalAmount=0L;
        LocalDateTime singleChargeTime = null;
        for(TollStreaming tollStreaming:vehicleEntryList){
            LocalTime lt = tollStreaming.getInOrOutTime().toLocalTime();
            if (vehicleType.isPresent()){
                if (!vehicleType.get().isTaxExemptVehicles()){
                    if(!isSpecialHoliday(tollStreaming.getInOrOutTime().toLocalDate()) && !isWeekend(tollStreaming.getInOrOutTime().toLocalDate())){
                        entryDetails = new EntryDetails();
                        Optional<TaxRule> taxRule = taxRuleImplementation.getTaxRuleByFromToTime(lt,cityData);
                        if (i==1)
                            singleChargeTime = tollStreaming.getInOrOutTime();
                        if(singleChargeTime.toLocalDate().equals(tollStreaming.getInOrOutTime().toLocalDate()) && (i != 1 )){
                            LocalTime entryTime = tollStreaming.getInOrOutTime().toLocalTime();
                            LocalTime singleHour = singleChargeTime.toLocalTime().plusHours(1);
                            if(entryTime.isBefore(singleHour))
                                continue;
                        }
                        if (taxRule.isPresent()){
                            entryDetails.setEntryNo(i++);
                            entryDetails.setEntryOrExitTime(tollStreaming.getInOrOutTime());
                            entryDetails.setAmount(taxRule.get().getPrice());
                            totalAmount = totalAmount + taxRule.get().getPrice();
                            entryDetailsList.add(entryDetails);
                        }
                    }
                }

            }
            singleChargeTime = tollStreaming.getInOrOutTime();
        }
        if(totalAmount>=60L)
            totalAmount = 60L;
        congestionTax.setTotalTaxAmount(totalAmount);
        congestionTax.setEntryAndExitDetailsList(entryDetailsList);
        return Optional.of(congestionTax);
    }
}
