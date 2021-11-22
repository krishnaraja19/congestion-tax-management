package com.country.congestiontaxmanagement.repository;

import com.country.congestiontaxmanagement.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByCityName(String cityName);
}
