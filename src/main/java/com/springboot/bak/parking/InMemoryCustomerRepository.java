package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryCustomerRepository implements DriverRepository {

    private Map<Long, Driver> customersById = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(0);

    @Override
    public Driver save(Driver driver) {
        if (driver.getId() == null) {
            final long newId = nextId.incrementAndGet();
            driver.setId(newId);
        }
        customersById.put(driver.getId(), driver);
        return driver;
    }

    @Override
    public Driver findById(long customerId) {
        return customersById.get(customerId);
    }
}
