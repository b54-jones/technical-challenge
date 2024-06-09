package com.ldms.benjones.ServiceTests;


import com.ldms.benjones.entity.InitiationDetails;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.service.ScheduleEntryService;
import com.ldms.benjones.service.ScheduleInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScheduleInfoServiceTest {

    @Autowired
    private ScheduleEntryService scheduleEntryService;

    @Autowired
    private ScheduleInfoService scheduleInfoService;

    @Test
    public void getScheduleInfoTest() {
        InitiationDetails details = new InitiationDetails(1L, 25000, 0.075, 5000, 0, 12);

        List<ScheduleEntry> entries = scheduleEntryService.generateEntries(details);

        ScheduleInfo actualInfo = scheduleInfoService.generateScheduleInfo(details, entries);
        double expectedMonthlyPayment = 1735.1483377081208;
        double expectedTotalInterest = 811.002733629987;
        double expectedTotalPayments = 25811.00273362999;

        Assertions.assertEquals(actualInfo.getMonthlyRepaymentAmount(), expectedMonthlyPayment);
        Assertions.assertEquals(actualInfo.getTotalInterest(), expectedTotalInterest);
        Assertions.assertEquals(actualInfo.getTotalPayments(), expectedTotalPayments);
    }
}
