package dev.hawknet37.airport_cache.service;

import com.google.common.cache.Cache;
import dev.hawknet37.airport_cache.entity.Airport;
import dev.hawknet37.airport_cache.entity.CacheEntry;
import dev.hawknet37.airport_cache.config.ConfigGuava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class AirportService {

    private final Map<String, Airport> mockDatabase = new HashMap<>();
    private final Cache<String, CacheEntry<Airport>> airportCache;
    private final long cacheExpirationMillis;

    @Autowired
    public AirportService(Cache<String, CacheEntry<Airport>> airportCache,
                          ConfigGuava configGuava
    ){
        this.airportCache = airportCache;
        this.cacheExpirationMillis = TimeUnit.MINUTES.toMillis(configGuava.getCacheExpirationMinutes());

        mockDatabase.put("JFK", new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", "America/New_York", 40.6413, -73.7781, "Runway details JFK (4 runways)", true));
        mockDatabase.put("LAX", new Airport("LAX", "Los Angeles International Airport", "Los Angeles", "USA", "America/Los_Angeles", 33.9416, -118.4085, "Runway details LAX (4 runways)", true));
        mockDatabase.put("ORD", new Airport("ORD", "O'Hare International Airport", "Chicago", "USA", "America/Chicago", 41.9742, -87.9073, "Runway details ORD (8 runways)", true));
        mockDatabase.put("SGN", new Airport("SGN", "Tan Son Nhat International Airport", "Ho Chi Minh City", "Vietnam", "Asia/Ho_Chi_Minh", 10.8171, 106.6577, "Runway details SGN (2 runways)", true));
        mockDatabase.put("HAN", new Airport("HAN", "Noi Bai International Airport", "Hanoi", "Vietnam", "Asia/Ho_Chi_Minh", 21.2227, 105.8078, "Runway details HAN (2 runways)", false));
    }

    public Airport getAirByCode(String code) {
        String upperCaseCode = code.toUpperCase();

        CacheEntry<Airport> cacheEntry = airportCache.getIfPresent(upperCaseCode);

        if (cacheEntry != null) {
            long remainingTimeMillis = (cacheEntry.getInsertionTimeMillis() + cacheExpirationMillis) - System.currentTimeMillis();
            long remainingSeconds = TimeUnit.MILLISECONDS.toSeconds(remainingTimeMillis);

            if (remainingSeconds < 0) remainingSeconds = 0;

            System.out.println("AirportService: CACHE HIT for airport code: " + upperCaseCode +
                    " (Remaining: " + remainingSeconds + " second)");
            return cacheEntry.getValue();
        }

        System.out.println("AirportService: CACHE MISS for airport code: " + upperCaseCode);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("AirportService: break");
        }

        Airport airport = mockDatabase.get(upperCaseCode);

        if (airport != null) {
            airportCache.put(upperCaseCode, new CacheEntry<>(airport));
            System.out.println("AirportService: Airport '" + upperCaseCode + "' loaded from 'mockDatabase' and added to cache.");
        } else {
            System.out.println("AirportService: Airport '" + upperCaseCode + "' NOT FOUND in 'mockDatabase'.");
        }
        return airport;
    }

    public List<Airport> getListAirport(){
        return new ArrayList<>(mockDatabase.values());
    }
}