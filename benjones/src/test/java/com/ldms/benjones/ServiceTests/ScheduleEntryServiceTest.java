package com.ldms.benjones.ServiceTests;

import com.ldms.benjones.entity.Schedule;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.service.ScheduleEntryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScheduleEntryServiceTest {

    @Autowired
    private ScheduleEntryService scheduleEntryService;

    @Test
    public void calculateMonthlyRepaymentTest() {
        Schedule schedule = new Schedule(1L, 25000, 0.075, 5000, 0, 60);
        double repayment = scheduleEntryService.calculateMonthlyRepayment(schedule);

        assertEquals(repayment, 400.7589719124706);
    }

    @Test
    public void calculateScheduleEntryTest() {
        ScheduleEntry expectedEntry = new ScheduleEntry(1L, 1, 1735.15, 1610.15, 125.0, 18389.85);
        ScheduleEntry actualEntry = scheduleEntryService.calculateScheduleEntry(0.00625, 20000, 1735.15, 1L, 1);

        assertEquals(actualEntry.getInterestPayment(), expectedEntry.getInterestPayment());
        assertEquals(actualEntry.getPrincipalPayment(), expectedEntry.getPrincipalPayment());
        assertEquals(actualEntry.getRemainingBalance(), actualEntry.getRemainingBalance());
    }
}
