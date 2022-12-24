package com.app.airport.repository;

import com.app.airport.models.Runways;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunwayRepository extends JpaRepository<Runways,Integer> {
}
