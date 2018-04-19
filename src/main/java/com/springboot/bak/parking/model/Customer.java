package com.springboot.bak.parking.model;

import java.util.Date;

public class Customer {
    private long id;
    private String vehicleNumber;
    private String registrationDate;

    public Customer() {
    }

    public Customer(long id, String vehicleNumber, String registrationDate) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.registrationDate = registrationDate;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
