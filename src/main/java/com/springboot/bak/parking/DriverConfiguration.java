package com.springboot.bak.parking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DriverConfiguration {

    @Bean
    DriverRepository driverRepository() {
        return new InMemoryCustomerRepository();
    }

    @Bean
    DriverService driverService(DriverRepository driverRepository, PriceRepository priceRepository) {
        return new DriverService(driverRepository, priceRepository, new ClockProvider() {
            @Override
            public Clock getClock() {
                return Clock.systemDefaultZone();
            }
        });
    }

}
