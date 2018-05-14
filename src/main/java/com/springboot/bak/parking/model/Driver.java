package com.springboot.bak.parking.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class Driver {

    private Long id;
    private String vehicleNumber;
    private LocalDateTime registrationDate;
    // 1 - > vip , 0 -> Regular
    private boolean vip;

    public Driver(String vehicleNumber, boolean vip) {
        this.vehicleNumber = vehicleNumber;
        this.vip = vip;
    }
    public Driver(){}

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isParked() {
        return registrationDate != null;
    }

    public void stopParking() {
        this.registrationDate = null;
    }

    public void startParking(LocalDateTime startDate) {
        this.registrationDate = startDate;
    }

}

