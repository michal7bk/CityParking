package com.springboot.bak.parking.model;

import java.time.LocalDateTime;


public class Customer {
    private long id;
    private String vehicleNumber;
    private LocalDateTime registrationDate;
    // 1 - > vip , 0 -> Regular
    private boolean vip;
    //1 - > vehicle in the parking, 0 -> vehicle outsie
    private boolean parked =true;

    public Customer() {
    }

    public Customer(long id, String vehicleNumber, boolean vip) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vip = vip;
        this.parked = true;
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


    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}

