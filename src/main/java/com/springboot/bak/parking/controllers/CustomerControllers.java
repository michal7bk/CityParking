package com.springboot.bak.parking.controllers;

import com.springboot.bak.parking.model.CurrenciesAllowed;
import com.springboot.bak.parking.model.Customer;
import com.springboot.bak.parking.model.PriceList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.springboot.bak.parking.model.PriceList.getPrice;


@RestController
public class CustomerControllers {
    private List<Customer> customers = new ArrayList<>();
    private AtomicLong  nextId = new AtomicLong();
    private PriceList priceList ;
    private CurrenciesAllowed currenciesAllowed = new CurrenciesAllowed("złoty","PLN", 1.0);

    @RequestMapping("/hello")
    public String sayHello(){

        return "hello spring boot app";
    }

// ZACZECIE PARKOWANIA -> DODANIE KIEROWCY//
    @PostMapping("/customer/start")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer startParking(@RequestBody Customer customer){
        customer.setId(nextId.incrementAndGet());
        customer.setRegistrationDate(LocalDateTime.now());
    customers.add(customer);
    return customer;
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customers;
    }

//    @GetMapping("/customers/{id}")
//    public  Customer getOneCustomer(@PathVariable("id") long customerId){
//for (Customer customer: customers){
//    if(customer.getId() == customerId){
//        return customer;
//    } }
//throw new IllegalArgumentException();
//    }


    //SPRAWDZENIE RACHUNKU //

    @GetMapping("/customer/bill/{id}")

    public double getPriceForParking(@PathVariable("id") long customerId){
        for (Customer customer: customers){
            if(customer.getId() == customerId || customer.isParked()){
                return getPrice(customer, currenciesAllowed);
            }
        }
        throw new IllegalArgumentException();
    }



    // WYJAZD Z PARKINGU -> ZAPLACENIE//
    @GetMapping("/customer/stop/{id}")
    public double editOneCustomer(
            @PathVariable("id") long customerId){
for (Customer customer: customers){
    if(customer.getId() == customerId){
        customer.setParked(false);
        return getPrice(customer,currenciesAllowed);
    }
}
throw new IllegalArgumentException();
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
    reason = "Request ID not found. ")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHanlder(){

    }






//
//    @PostMapping("/customers/{id}")
//    public  Customer editOneCustomer(
//            @PathVariable("id") long customerId,
//            @RequestBody Customer   newCustomer){
//        for (Customer customer: customers){
//            if(customer.getId() == customerId){
//                customers.remove(customer);
//                newCustomer.setId(customerId);
//                customers.add(newCustomer);
//                return customer;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
//            reason = "Request ID not found. ")
//    @ExceptionHandler(IllegalArgumentException.class)
//    public void badIdExceptionHanlder(){
//        //Nothing to do
//    }


}
