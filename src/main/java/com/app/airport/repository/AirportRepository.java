package com.app.airport.repository;

import com.app.airport.models.TopCountries;
import com.app.airport.models.Airports;
import com.app.airport.models.Runways;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repo for Airports
 */
@Repository
public interface AirportRepository extends JpaRepository<Airports, Integer> {

    /**
     * getRunwaysByCountryCode
     * @param countryCode countryCode
     * @return runways
     */
    @Query("select runways from Airports where iso_country= ?1")
    List<Runways> getRunwaysByCountryCode(String countryCode);

    /**
     * returns the top countries with max number of airports
     * @param topAirports number of top airports to retrieve
     * @return top countries
     */
    @Query(value = "select iso_country as Country,count(id) as AirportCount from Airports group by Country order by AirportCount desc Limit ?1",nativeQuery = true)
    List<TopCountries> getTopCountries(int topAirports);
}
