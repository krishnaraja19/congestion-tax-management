package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.City;
import com.country.congestiontaxmanagement.model.TaxRule;
import com.country.congestiontaxmanagement.repository.TaxRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class TaxRuleImplementation {

    @Autowired
    TaxRuleRepository taxRuleRepository;

    public Optional<TaxRule> getTaxRuleByFromToTime(LocalTime time, Optional<City> city){
        Long cityId = 0L;
        if(city.isPresent())
            cityId = city.get().getId();
         return taxRuleRepository.findByFromTimeLessThanEqualAndToTimeGreaterThanEqualAndCity_Id(time,time,cityId);
    }
}
