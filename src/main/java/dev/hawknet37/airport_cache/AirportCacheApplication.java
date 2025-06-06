package dev.hawknet37.airport_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration; // Thêm dòng này
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class AirportCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportCacheApplication.class, args);
    }

}
