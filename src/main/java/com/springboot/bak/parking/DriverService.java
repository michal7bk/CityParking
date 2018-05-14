package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Currency;
import com.springboot.bak.parking.model.Driver;
import com.springboot.bak.parking.model.Money;

import java.time.LocalDateTime;

class DriverService {

    private final DriverRepository driverRepository;
    private final PriceRepository priceRepository;
    private final ClockProvider clockProvider;

    DriverService(DriverRepository driverRepository, PriceRepository priceRepository, ClockProvider clockProvider) {
        this.driverRepository = driverRepository;
        this.priceRepository = priceRepository;
        this.clockProvider = clockProvider;
    }

    public Driver startParking(Driver driver) {
        driver.startParking(now());
        return driverRepository.save(driver);
    }

    private LocalDateTime now() {
        return LocalDateTime.now(clockProvider.getClock());
    }

    public Driver findById(long customerId) {
        Driver driver = driverRepository.findById(customerId);
        if (driver == null) {
            throw new CustomerNotFoundException();
        }
        return driver;
    }

    public Money checkBill(long driverId) {
        Driver driver = findById(driverId);
        return checkBill(driver);
    }

    public Money stopParking(long driverId) {
        Driver driver = findById(driverId);
        Money bill = checkBill(driver);
        driver.stopParking();
        return bill;
    }

    private Money checkBill(Driver driver) {
        double price = 0 ;
        Currency currency = Currency.PLN;
        LocalDateTime currentDate = now();
        long diffrenceInHours=0;

        long diffrenceInSeconds = (java.time.Duration.between(driver.getRegistrationDate(), currentDate).getSeconds());
        if(diffrenceInSeconds%3600==0)
        {diffrenceInHours = (diffrenceInSeconds / 3600);}
        else
        {diffrenceInHours = (diffrenceInSeconds / 3600)+1;}


        if(driver.isVip()){
            if (diffrenceInHours <= 1){
                price = 0;
            }else if( diffrenceInHours <= 2){
                price = 2* currency.getRate();
            }else {
                for(long i =3;i <= diffrenceInHours;i++) {
                    price = price + currency.getRate() * countNextHour(i, 1.2);
                }
                price= price  + 2  * currency.getRate();
            }
        }else{
            if (diffrenceInHours <= 1){
                price = 1 * currency.getRate();
            }else if( diffrenceInHours <= 2){
                price = 2* currency.getRate() + 1 * currency.getRate();
            }else {
                for(long i =3;i <= diffrenceInHours;i++) {
                    price = price + currency.getRate() * countNextHour(i, 1.5);
                }
                price= price +  1  * currency.getRate() + 2  * currency.getRate();
            }
        }
        double amount = Math.round(price * 100) / 100.d;
        return new Money(amount, currency);
    }

    private double countNextHour(long hours, double multiplier  ){
        if (hours <= 2)
            return 2.0;
        else
            return multiplier * countNextHour(hours - 1, multiplier) ;
    }

}
