package com.ldms.benjones.ServiceTests;

import com.ldms.benjones.utils.ScheduleInfo;
import com.ldms.benjones.entity.Schedule;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.repository.ScheduleEntryRepository;
import com.ldms.benjones.service.ScheduleEntryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@SpringBootTest
public class ScheduleEntryServiceTest {

    @InjectMocks
    private ScheduleEntryService scheduleEntryService;

    @Mock
    private ScheduleEntryRepository scheduleEntryRepository;

    @Test
    public void calculateMonthlyRepaymentTest() {
        Schedule schedule = new Schedule(1L, 25000, 0.075, 5000, 0, 60);

        double actualRepayment = scheduleEntryService.calculateMonthlyRepayment(schedule);
        double expectedRepayment = 400.7589719124706;

        assertEquals(actualRepayment, expectedRepayment);
    }

    @Test
    public void generateScheduleEntriesTest() {
        Schedule schedule = new Schedule(1L, 25000, 0.075, 5000, 0, 60);
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

    @Test
    public void getScheduleInfoTest() {
        Schedule schedule = new Schedule(1L, 25000, 0.075, 5000, 0, 12);

        List<ScheduleEntry> entries = scheduleEntryService.generateEntries(schedule);
        Mockito.when(scheduleEntryRepository.findByScheduleId(1L)).thenReturn(Optional.ofNullable(entries));

        ScheduleInfo actualInfo = scheduleEntryService.getScheduleInfo(schedule);
        double expectedMonthlyPayment = 1735.1483377081208;
        double expectedTotalInterest = 811.002733629987;
        double expectedTotalPayments = 25811.00273362999;

        Assertions.assertEquals(actualInfo.getMonthlyRepaymentAmount(), expectedMonthlyPayment);
        Assertions.assertEquals(actualInfo.getTotalInterest(), expectedTotalInterest);
        Assertions.assertEquals(actualInfo.getTotalPayments(), expectedTotalPayments);
    }
}
