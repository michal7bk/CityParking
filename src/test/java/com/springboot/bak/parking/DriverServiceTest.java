package com.springboot.bak.parking;

import com.springboot.bak.parking.model.Currency;
import com.springboot.bak.parking.model.Driver;
import com.springboot.bak.parking.model.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;


public class DriverServiceTest {

    public static final boolean NO_VIP = false;
    public static final boolean VIP = true;
    public static final boolean PARKED = true;


    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;
    @Mock
    private PriceRepository priceRepository;

    private MockClockProvider clockProvider;

    @Before
    public void setUp() {
        initMocks(this);
        this.clockProvider = new MockClockProvider();
        this.driverService = new DriverService(driverRepository, priceRepository, clockProvider);
    }

    @Test
    public void shouldStartParking() {
        //given
        Driver newDriver = new Driver("WW-111", NO_VIP);
        given(driverRepository.save(eq(newDriver))).willReturn(newDriver);
        //when
        Driver resultDriver = driverService.startParking(newDriver);
        //then
        assertThat(resultDriver.isParked()).isTrue();
    }

    @Test
    public void shouldBillForLessThan1HourOfParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(NO_VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(30, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(1, Currency.PLN));
    }

    @Test
    public void shouldBillFor1HourOfParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(NO_VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(1, ChronoUnit.HOURS);

        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(1, Currency.PLN));
    }

    @Test
    public void shouldBillForMoreThan1HourOfParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(NO_VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(61, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(3, Currency.PLN));
    }

    @Test
    public void shouldBillForMoreThan3HourOfParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(NO_VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(181, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(10.5, Currency.PLN));
    }

    @Test
    public void shouldBillForLessThan1HourOfVipParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(30, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(0, Currency.PLN));
    }

    @Test
    public void shouldBillFor1HourOfVipParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(1, ChronoUnit.HOURS);

        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(0, Currency.PLN));
    }

    @Test
    public void shouldBillForMoreThan1HourOfVipParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(61, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(2, Currency.PLN));
    }

    @Test
    public void shouldBillForMoreThan3HourOfVipParking() {
        //given
        Driver driver = mock(Driver.class);
        long driverId = 2L;
        given(driver.getId()).willReturn(driverId);
        given(driver.getRegistrationDate()).willReturn(clockProvider.now());
        given(driver.isVip()).willReturn(VIP);
        given(driver.isParked()).willReturn(PARKED);
        given(driverRepository.findById(eq(driverId))).willReturn(driver);

        //when
        Driver resultDriver = driverService.startParking(driver);
        clockProvider.moveForward(181, ChronoUnit.MINUTES);
        Money bill = driverService.checkBill(driverId);
        //then
        assertThat(bill).isEqualTo(new Money(7.28, Currency.PLN));
    }

    private static class MockClockProvider implements ClockProvider {

        private static final LocalDateTime REFERENCE_DATE_TIME = LocalDateTime.of(2010, Month.JANUARY, 1, 10, 0); //2016-04-01 at 10:00am
        private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

        private Clock fixedClock;

        public MockClockProvider() {
            setFixedClock(REFERENCE_DATE_TIME);
        }

        public void moveForward(long amount, TemporalUnit unit) {
            setFixedClock(now().plus(amount, unit));
        }

        private void setFixedClock(LocalDateTime referenceDateTime) {
            this.fixedClock = Clock.fixed(referenceDateTime.atZone(DEFAULT_ZONE).toInstant(), DEFAULT_ZONE);
        }

        @Override
        public Clock getClock() {
            return fixedClock;
        }


        public LocalDateTime now() {
            return LocalDateTime.now(fixedClock);
        }
    }
}