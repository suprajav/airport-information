package com.app.airport.controller;

import com.app.airport.models.TopCountries;
import com.app.airport.models.Runways;
import com.app.airport.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Controller class for Airport data
 */
@RestController
public class AirportController {

    private final AirportService airportService;

    /**
     * constructor
     * @param airportService airportService
     */
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * get runways based on country code or country name
     * @param countryCode code of the country. Ex: "AT"
     * @param countryName name of the country. Ex: "Austria"
     * @return List<Runways> All the runways in all airports in the country
     */
    @GetMapping("/runways")
    public ResponseEntity<List<Runways>> getRunways(@RequestParam(required = false) String countryCode, @RequestParam(required = false) String countryName) {
        if (countryCode == null && countryName == null) {
             throw new ConstraintViolationException("CountryCode or CountryName must be provided!",null);
        }
        return ResponseEntity.ok(airportService.getRunways(countryCode, countryName));
    }

    /**
     * retrieves the top countries that have max airports
     * @param number of topAirports
     * @return top countries that have max airports
     */
    @GetMapping("/countries")
    public ResponseEntity<List<TopCountries>> getTopCountries(@RequestParam Integer topAirports) {
        return ResponseEntity.ok(airportService.getTopCountries(topAirports));
    }
}
