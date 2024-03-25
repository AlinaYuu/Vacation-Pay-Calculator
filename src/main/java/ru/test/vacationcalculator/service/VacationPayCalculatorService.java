package ru.test.vacationcalculator.service;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VacationPayCalculatorService {
    private static final double AVERAGE_NUMBER_OF_DAYS_WORKED = 29.3;
    private static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

    public double calculateVacationPay(double averageSalary12Months, int vacationDays) {
        if (averageSalary12Months <= 0 || vacationDays <= 0) {
            throw new IllegalArgumentException("The salary and the number of vacation days must be greater than 0");
        }
        double result = (averageSalary12Months / AVERAGE_NUMBER_OF_DAYS_WORKED) * vacationDays;
        result = Math.round(result * 100.0) / 100.0;

        return result;
    }

    public double calculateVacationPayWithDates(double averageSalary12Months,
                                                @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                                @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        if (averageSalary12Months <= 0 || startDate == null || endDate == null) {
            throw new IllegalArgumentException("The salary and the start and end dates of the vacation must be correct");
        }
        int vacationDays = calculateNumberOfDaysWorked(startDate, endDate);
        double result = (averageSalary12Months / AVERAGE_NUMBER_OF_DAYS_WORKED) * vacationDays;
        result = Math.round(result * 100.0) / 100.0;

        return result;
    }

    private int calculateNumberOfDaysWorked(@DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
                                            @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        if (startDate == null || endDate == null || endDate.before(startDate)) {
            throw new IllegalArgumentException("The start and end dates of the vacation must be set correctly");
        }
        long differenceInMillis = endDate.getTime() - startDate.getTime();
        return (int) (differenceInMillis / MILLISECONDS_IN_DAY) + 1;
    }
}

