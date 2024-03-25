package ru.test.vacationcalculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VacationPayCalculatorServiceTests {
    private VacationPayCalculatorService vacationPayCalculatorService;

    @BeforeEach
    void setUp() {
        vacationPayCalculatorService = new VacationPayCalculatorService();
    }

    @Test
    void calculateVacationPayTest() {
        double averageSalary12Months = 15000.0;
        int vacationDays = 7;

        double result = vacationPayCalculatorService.calculateVacationPay(averageSalary12Months, vacationDays);

        Assertions.assertEquals(3583.62, result);
    }

    @Test
    void calculateVacationPayWithDatesTest() throws Exception {
        double averageSalary12Months = 15000.0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = simpleDateFormat.parse("24/03/2024");
        Date endDate = simpleDateFormat.parse("30/03/2024");

        double result = vacationPayCalculatorService.calculateVacationPayWithDates(averageSalary12Months, startDate, endDate);

        assertEquals(3583.62, result);
    }

    @Test
    void calculateVacationPayWithZeroSalaryTest() {
        double averageSalary12Months = 0.0;
        int vacationDays = 7;

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> vacationPayCalculatorService.calculateVacationPay(averageSalary12Months, vacationDays)
        );
    }

    @Test
    void calculateVacationPayWithNegativeVacationDaysTest() {
        double averageSalary12Months = 15000.0;
        int vacationDays = -100;

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> vacationPayCalculatorService.calculateVacationPay(averageSalary12Months, vacationDays)
        );
    }

    @Test
    void calculateVacationPayWithNullStartDateTest() {
        double averageSalary12Months = 15000.0;
        Date startDate = null;
        Date endDate = new Date();

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> vacationPayCalculatorService.calculateVacationPayWithDates(averageSalary12Months, startDate, endDate)
        );
    }

    @Test
    void calculateVacationPayWithEndDateBeforeStartDateTest() throws Exception {
        double averageSalary12Months = 15000.0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat.parse("29/03/2024");
        Date endDate = dateFormat.parse("24/03/2024");

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> vacationPayCalculatorService.calculateVacationPayWithDates(averageSalary12Months, startDate, endDate)
        );
    }

}