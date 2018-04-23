package com.springboot.bak.parking.controllers;

import com.springboot.bak.parking.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerControllers {
    private List<Customer> customers = new ArrayList<>();
    private AtomicLong  nextId = new AtomicLong();

    @RequestMapping("/hello")
    public String sayHello(){

        return "hello spring boot app";
    }


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody Customer customer){
        customer.setId(nextId.incrementAndGet());
        customer.setRegistrationDate(LocalDateTime.now());
    customers.add(customer);
    return customer;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customers;
    }

    @GetMapping("/customers/{id}")
    public  Customer getOneCustomer(@PathVariable("id") long customerId){
for (Customer customer: customers){
    if(customer.getId() == customerId){
        return customer;
    }
}
throw new IllegalArgumentException();
    }

    @GetMapping("/customers/{id}")
    public  Customer getPriceForParking(@PathVariable("id") long customerId){
        for (Customer customer: customers){
            if(customer.getId() == customerId){
                return customer;
            }
        }
        throw new IllegalArgumentException();
    }


    @PostMapping("/customers/{id}")
    public  Customer editOneCustomer(
            @PathVariable("id") long customerId,
            @RequestBody Customer   newCustomer){
for (Customer customer: customers){
    if(customer.getId() == customerId){
        customers.remove(customer);
        newCustomer.setId(customerId);
        customers.add(newCustomer);
        return customer;
    }
}
throw new IllegalArgumentException();
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
    reason = "Request ID not found. ")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHanlder(){
        //Nothing to do
    }


}
