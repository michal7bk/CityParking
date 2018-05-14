package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Driver;
import com.springboot.bak.parking.model.Money;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController("drivers")
public class DriverController {

    private final DriverRepository driverRepository;
    private final DriverService driverService;

    public DriverController(DriverRepository driverRepository, DriverService driverService) {
        this.driverRepository = driverRepository;
        this.driverService = driverService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver startParking(@RequestBody Driver driver) {
        return driverService.startParking(driver);
    }

    @GetMapping("/{id}")
    //TODO return 404 if not found
    public Driver getOneCustomer(@PathVariable("id") long customerId) {
        return driverService.findById(customerId);
    }


    @GetMapping("/{id}/check")
    public Money checkCustomerBill(@PathVariable("id") long customerId) {
        return driverService.checkBill(customerId);
    }

    @GetMapping("/{id}/stop/")
    public Money stopParking(@PathVariable("id") long customerId) {
        return driverService.stopParking(customerId);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found. ")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHanlder() {
    }

}
