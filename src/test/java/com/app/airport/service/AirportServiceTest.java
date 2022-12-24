package com.app.airport.service;

import com.app.airport.models.Airports;
import com.app.airport.models.Runways;
import com.app.airport.models.TopCountries;
import com.app.airport.repository.AirportRepository;
import com.app.airport.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Unit test class for Airport Service.
 */
class AirportServiceTest {
    @Mock
    AirportRepository airportRepository;
    @Mock
    CountryRepository countryRepository;

    private AirportService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new AirportService(airportRepository, countryRepository);
    }

    @Test
    void shouldReturnRunwaysIfCountryCodeIsProvided() {
        String countryCode = "AT";
        when(airportRepository.getRunwaysByCountryCode(countryCode)).thenReturn(getExpectedRunways());

        List<Runways> actualRunways = sut.getRunways(countryCode, null);

        assertEquals(getExpectedRunways(), actualRunways);
    }

    @Test
    void shouldReturnEmptyListIfCountryCodeIsNotPresent() {
        String countryCode = "ZZ";
        when(airportRepository.getRunwaysByCountryCode(countryCode)).thenReturn(new ArrayList<>());

        List<Runways> actualRunways = sut.getRunways(countryCode, null);

        assertEquals(0, actualRunways.size());
    }

    @Test
    void shouldReturnRunwaysIfCountryNameIsProvided() {
        String countryName = "Austria";

        Airports airports = new Airports(6523, "00A", "small", "Test Airport", 122.2, 2234.2, 12,
                "Europe", "AT", "AT-001", "", "", "AAA", "", "", "abc.com",
                "", "keywords", getExpectedRunways());
        List<Airports> airportList = new ArrayList<>();
        airportList.add(airports);
        when(countryRepository.getAirportsByName(countryName)).thenReturn(airportList);

        sut.getRunways(null, countryName);

        assertEquals(getExpectedRunways(), airportList.stream().iterator().next().getRunways());
    }

    @Test
    void shouldReturnEmptyListIfCountryNameIsNotPresent() {
        String countryName = "NotNetherlands";

        when(countryRepository.getAirportsByName(countryName)).thenReturn(new ArrayList<>());

        List<Runways> runways = sut.getRunways(null, countryName);

        assertEquals(0,  runways.size());
    }

    @Test
    void shouldReturnTopCountriesBasedOnAirports() {
        int count = 1;
        TopCountries expectedTopCountries = new TopCountries() {
            @Override
            public String getCountry() {
                return "US";
            }

            @Override
            public int getAirportCount() {
                return 23280;
            }
        };
        List<TopCountries> expectedList = new ArrayList<>();
        expectedList.add(expectedTopCountries);
        when(airportRepository.getTopCountries(count)).thenReturn(expectedList);

        List<TopCountries> actualTopCountries = sut.getTopCountries(count);

        assertEquals(expectedList,actualTopCountries);
    }

    private List<Runways> getExpectedRunways() {
        List<Runways> expectedRunways = new ArrayList<>();
        Runways runways = new Runways(233574, 4430, "LOWG", 9842, 148, "ASP", true,
                false, "17C", 47.0043, 15.436, null, 169.0,
                null, "35C", 46.9778, 15.4433, null, 349.0,
                null);
        expectedRunways.add(runways);

        return expectedRunways;
    }

}