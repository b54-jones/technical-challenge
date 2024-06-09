package com.ldms.benjones.ServiceTests;

import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.entity.InitiationDetails;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.repository.ScheduleEntryRepository;
import com.ldms.benjones.service.ScheduleEntryService;
import com.ldms.benjones.service.ScheduleInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScheduleEntryServiceTest {

    @Autowired
    private ScheduleEntryService scheduleEntryService;

    @Test
    public void calculateMonthlyRepaymentTest() {
        InitiationDetails schedule = new InitiationDetails(1L, 25000, 0.075, 5000, 0, 60);

        double actualRepayment = scheduleEntryService.calculateMonthlyRepayment(schedule);
        double expectedRepayment = 400.7589719124706;

        assertEquals(actualRepayment, expectedRepayment);
    }

    @Test
    public void generateEnoughScheduleEntriesTest() {
        InitiationDetails schedule = new InitiationDetails(1L, 25000, 0.075, 5000, 0, 60);
        List<ScheduleEntry> scheduleEntries = scheduleEntryService.generateEntries(schedule);
        assertEquals(scheduleEntries.size(), schedule.getNumberOfRepayments());
    }

    @Test
    public void calculateScheduleEntryTest() {
        ScheduleEntry expectedEntry = new ScheduleEntry(1L, 1, 1735.15, 1610.15, 125.0, 18389.85);
        ScheduleEntry actualEntry = scheduleEntryService.calculateScheduleEntry(0.00625, 20000, 1735.15, 1L, 1);

        assertEquals(actualEntry.getInterestPayment(), expectedEntry.getInterestPayment());
        assertEquals(actualEntry.getPrincipalPayment(), expectedEntry.getPrincipalPayment());
        assertEquals(actualEntry.getRemainingBalance(), expectedEntry.getRemainingBalance());
    }
}
