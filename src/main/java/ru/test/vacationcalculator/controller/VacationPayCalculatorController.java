package ru.test.vacationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.test.vacationcalculator.service.VacationPayCalculatorService;

import java.util.Date;

@RestController
public class VacationPayCalculatorController {
    private final VacationPayCalculatorService vacationPayCalculatorService;

    @Autowired
    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateVacationPay(@RequestParam("salary") double averageSalary12Months,
                                                       @RequestParam("numberOfDays") int vacationDays) {
        double vacationPay = vacationPayCalculatorService.calculateVacationPay(averageSalary12Months, vacationDays);
        return new ResponseEntity<>(vacationPay, HttpStatus.OK);
    }

    @GetMapping("/calculateWithDates")
    public ResponseEntity<Double> calculateVacationPayWithDates(@RequestParam("salary") double averageSalary12Months,
                                                                @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                                @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        double vacationPay = vacationPayCalculatorService.calculateVacationPayWithDates(averageSalary12Months, startDate, endDate);
        return new ResponseEntity<>(vacationPay, HttpStatus.OK);
    }
}
