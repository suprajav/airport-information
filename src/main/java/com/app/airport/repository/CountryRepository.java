package com.app.airport.repository;

import com.app.airport.models.Airports;
import com.app.airport.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Countries,Integer> {
    @Query("select airports from Countries where name like ?1%")
    List<Airports> getAirportsByName(String name);
}
