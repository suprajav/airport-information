package com.app.airport.service;

import com.app.airport.models.TopCountries;
import com.app.airport.models.Airports;
import com.app.airport.models.Runways;
import com.app.airport.repository.AirportRepository;
import com.app.airport.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for retrieving runways and top countries
 */
@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;

    /**
     * Constructor
     * @param airportRepository airportRepository
     * @param countryRepository countryRepository
     */
    public AirportService(AirportRepository airportRepository,
                          CountryRepository countryRepository) {
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
    }

    /**
     * method to return the runways based on country code or country name
     * @param countryCode countryCode
     * @param countryName countryName
     * @return List of runways
     */
    public List<Runways> getRunways(String countryCode, String countryName)  {
        if (countryName != null) {
            List<Airports> airports = countryRepository.getAirportsByName(countryName);
            return airports.isEmpty()? new ArrayList<>(): airports.stream().iterator().next().getRunways();
            }
        return airportRepository.getRunwaysByCountryCode(countryCode);
    }

    /**
     * get Top Countries with max airports
     * @param topAirports topAirports
     * @return List of top countries
     */
    public List<TopCountries> getTopCountries(int topAirports) {
        return airportRepository.getTopCountries(topAirports);

    }
}
