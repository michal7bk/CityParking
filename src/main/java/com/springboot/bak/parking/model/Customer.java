package com.springboot.bak.parking.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


public class Customer {
    private long id;
    private String vehicleNumber;
    private LocalDateTime registrationDate;

    // 1 - > Vip , 0 -> Regular
    private boolean vip;
//    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Customer() {
    }

    public Customer(long id, String vehicleNumber, boolean vip) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vip = vip;
       // this.registrationDate = new Date();
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

//    public String getRegistrationDate() {
//        return dateFormat.format(registrationDate);
//    }


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
}

