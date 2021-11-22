package com.country.congestiontaxmanagement.repository;

import com.country.congestiontaxmanagement.model.SpecialHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialHolidaysRepository extends JpaRepository<SpecialHoliday,Long> {

}
