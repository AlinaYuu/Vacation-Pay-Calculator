package ru.test.vacationcalculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.test.vacationcalculator.service.VacationPayCalculatorService;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayCalculatorController.class)
class VacationPayCalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationPayCalculatorService vacationPayCalculatorService;

    @Test
    void calculateVacationPayTest() throws Exception {
        double averageSalary12Months = 15000.0;
        int vacationDays = 7;
        double vacationPay = 3583.62;

        when(vacationPayCalculatorService.calculateVacationPay(averageSalary12Months, vacationDays)).thenReturn(vacationPay);

        mockMvc.perform(get("/calculate")
                        .param("salary", String.valueOf(averageSalary12Months))
                        .param("numberOfDays", String.valueOf(vacationDays)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(vacationPay)));
    }

    @Test
    void calculateVacationPayWithDatesTest() throws Exception {
        double averageSalary12Months = 15000.0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = simpleDateFormat.parse("24/03/2024");
        Date endDate = simpleDateFormat.parse("30/03/2024");
        double vacationPay = 3583.62;

        when(vacationPayCalculatorService.calculateVacationPayWithDates(averageSalary12Months, startDate, endDate)).thenReturn(vacationPay);

        mockMvc.perform(get("/calculateWithDates")
                        .param("salary", String.valueOf(averageSalary12Months))
                        .param("startDate", simpleDateFormat.format(startDate))
                        .param("endDate", simpleDateFormat.format(endDate)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(vacationPay)));
    }
}
