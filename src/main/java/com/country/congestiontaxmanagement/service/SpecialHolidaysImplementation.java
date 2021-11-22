package com.country.congestiontaxmanagement.service;

import com.country.congestiontaxmanagement.model.SpecialHoliday;
import com.country.congestiontaxmanagement.repository.SpecialHolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialHolidaysImplementation {

    @Autowired
    SpecialHolidaysRepository specialHolidaysRepository;

    public List<SpecialHoliday> getSpecialHolidays(){
        return specialHolidaysRepository.findAll();
    }

    public void saveHoliday(SpecialHoliday specialHoliday){
        specialHolidaysRepository.save(specialHoliday);
    }
}
