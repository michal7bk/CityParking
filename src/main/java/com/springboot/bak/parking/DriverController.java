package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Driver;
import com.springboot.bak.parking.model.Money;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/drivers")
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
    public Driver getOneDriver(@PathVariable("id") long customerId) {
        this.isFilmExists(customerId);
        return driverService.findById(customerId);
    }


    @GetMapping("/{id}/check")
    public Money checkDriverBill(@PathVariable("id") long customerId) {
        return driverService.checkBill(customerId);
    }

    @GetMapping("/{id}/stop")
    public Money stopParking(@PathVariable("id") long customerId) {
        return driverService.stopParking(customerId);
    }



    private void isFilmExists(Long id){
        if(driverService.findById(id) == null){
            new DriverNotFoundException(id);
        }
    }

}
