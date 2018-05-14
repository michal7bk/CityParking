package com.springboot.bak.parking;


import com.springboot.bak.parking.model.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ParkingApplication.class
)

@AutoConfigureWebMvc
public class ParkingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private DriverRepository repository;

    @Test
    public void shouldCreateNewDriver() {
        //given
        String vehicleNumber = "123NUMBER";
        Driver newDriver = new Driver("123NUMBER", false);
        //when
        ResponseEntity<Driver> responseEntity = template.postForEntity("/drivers", newDriver, Driver.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Driver responseDriver = responseEntity.getBody();
        assertThat(responseDriver).isNotNull();
        assertThat(responseDriver.getId()).isNotNull();
        assertThat(responseDriver.getVehicleNumber()).isEqualTo(vehicleNumber);
    }



}
