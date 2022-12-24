package com.app.airport.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Runways Entity
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Runways {
    @Id
    private int id;
    private int airport_ref;
    private String airport_ident;
    private int length_ft;
    private int width_ft;
    private String surface;
    private Boolean lighted;
    private Boolean closed;
    private String le_ident;
    private Double le_latitude_deg;
    private Double le_longitude_deg;
    private Long le_elevation_ft;
    private Double le_heading_degT;
    private Long le_displaced_threshold_ft;
    private String he_ident;
    private Double he_latitude_deg;
    private Double he_longitude_deg;
    private Long he_elevation_ft;
    private Double he_heading_degT;
    private Long he_displaced_threshold_ft;
}
