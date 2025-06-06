package dev.hawknet37.airport_cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    private String code;
    private String name;
    private String city;
    private String country;
    private String timezone;
    private double latitude;
    private double longitude;
    private String runwayDetails;
    private boolean isHub;
}
