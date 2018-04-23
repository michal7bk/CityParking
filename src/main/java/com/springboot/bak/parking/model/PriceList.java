package com.springboot.bak.parking.model;

import java.time.LocalDateTime;


public class PriceList {



    public static double getPrice(Customer customer,CurrenciesAllowed currency ){
        double price = 0 ;
        LocalDateTime currentDate = LocalDateTime.now();
        long diffrenceInHours = ((java.time.Duration.between(customer.getRegistrationDate(), currentDate).toMinutes())/60)+1;

        if(customer.isVip()){
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
        return Math.round(price*100)/100.d;
    }

    private   static double countNextHour(long hours, double multiplier  ){
        if (hours <= 2)
            return 2.0;
        else
            return multiplier * countNextHour(hours - 1, multiplier) ;
    }


}
