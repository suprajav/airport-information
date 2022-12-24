package com.app.airport.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Airports Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Airports {
    @Id
    private int id;
    private String ident;
    private String type;
    private String name;
    private Double latitude_deg;
    private Double longitude_deg;
    private int elevation_ft;
    private String continent;
    private String iso_country;
    private String iso_region;
    private String municipality;
    private String scheduled_service;
    private String gps_code;
    private String iata_code;
    private String local_code;
    private String home_link;
    private String wikipedia_link;
    @Lob
    private String keywords;

    @OneToMany(targetEntity = Runways.class, mappedBy = "airport_ref", fetch = FetchType.EAGER)
    private List<Runways> runways;
}
