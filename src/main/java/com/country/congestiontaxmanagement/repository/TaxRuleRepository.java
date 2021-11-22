package com.country.congestiontaxmanagement.repository;

import com.country.congestiontaxmanagement.model.TaxRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaxRuleRepository extends JpaRepository<TaxRule,Long> {
        Optional<TaxRule> findByFromTimeLessThanEqualAndToTimeGreaterThanEqualAndCity_Id(
                LocalTime entryTime, LocalTime entryTime1,Long cityId);
}
