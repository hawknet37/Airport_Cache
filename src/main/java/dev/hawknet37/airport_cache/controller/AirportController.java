package dev.hawknet37.airport_cache.controller;

import dev.hawknet37.airport_cache.entity.Airport;
import dev.hawknet37.airport_cache.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/{code}")
    public Airport getAirportByCode(@PathVariable String code){
        return airportService.getAirByCode(code);
    }

    @GetMapping
    public List<Airport> getListAirport(){
        return airportService.getListAirport();
    }
}