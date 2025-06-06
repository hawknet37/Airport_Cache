package dev.hawknet37.airport_cache.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import dev.hawknet37.airport_cache.entity.Airport;
import dev.hawknet37.airport_cache.entity.CacheEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ConfigGuava {
    private final long CACHE_EXPIRATION_MINUTES = 1;

    @Bean
    public Cache<String, CacheEntry<Airport>> airportCache(){
        return CacheBuilder.newBuilder()
                .expireAfterWrite(CACHE_EXPIRATION_MINUTES, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    public long getCacheExpirationMinutes() {
        return CACHE_EXPIRATION_MINUTES;
    }
}
