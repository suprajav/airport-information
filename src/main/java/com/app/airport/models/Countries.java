package com.app.airport.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Countries Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Countries {
    @Id
    private int id;
    private String code;
    private String name;
    private String continent;
    private String wikipedia_link;
    private String keywords;

    @OneToMany(targetEntity = Airports.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "iso_country", referencedColumnName = "code")
    private List<Airports> airports;
}
