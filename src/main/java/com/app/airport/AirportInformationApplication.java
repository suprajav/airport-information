package com.app.airport;


import com.app.airport.models.Airports;
import com.app.airport.models.Countries;
import com.app.airport.models.Runways;
import com.app.airport.repository.AirportRepository;
import com.app.airport.repository.CountryRepository;
import com.app.airport.repository.RunwayRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * AirportInformationApplication
 * This application reads the airport data from a CSV
 * and loads the data into a in-memory database during start up
 */
@SpringBootApplication
public class AirportInformationApplication {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    RunwayRepository runwayRepository;
    @Autowired
    AirportRepository airportRepository;

    /**
     * Main method of the application
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(AirportInformationApplication.class, args);
    }

    /**
     * readFromCSV
     * @throws FileNotFoundException
     */
    @PostConstruct
    public void loadDataFromCSVToDatabase() throws FileNotFoundException {
        List<Countries> countries = new CsvToBeanBuilder(new FileReader("countries.csv"))
                .withType(Countries.class)
                .build()
                .parse();
        countryRepository.saveAll(countries);

        List<Runways> runways = new CsvToBeanBuilder(new FileReader("runways.csv"))
                .withType(Runways.class)
                .build()
                .parse();
        runwayRepository.saveAll(runways);

        List<Airports> airports= new CsvToBeanBuilder(new FileReader("airports.csv"))
                .withType(Airports.class)
                .build()
                .parse();
        airportRepository.saveAll(airports);
    }
}
