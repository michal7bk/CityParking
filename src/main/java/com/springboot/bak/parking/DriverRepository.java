package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Driver;

public interface DriverRepository {

    Driver save(Driver driver);

    Driver findById(long customerId);
}
