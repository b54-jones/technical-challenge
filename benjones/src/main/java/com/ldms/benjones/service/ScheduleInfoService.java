package com.ldms.benjones.service;

import com.ldms.benjones.entity.InitiationDetails;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.repository.ScheduleInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleInfoService {


    @Autowired
    private final ScheduleInfoRepository scheduleInfoRepository;

    public ScheduleInfoService(ScheduleInfoRepository scheduleInfoRepository) {
        this.scheduleInfoRepository = scheduleInfoRepository;
    }

    public ScheduleInfo generateScheduleInfo(InitiationDetails initiationDetails, List<ScheduleEntry> entries) {
        double totalInterest = entries.stream().mapToDouble(ScheduleEntry::getInterestPayment).sum();
        double totalPayments = entries.stream().mapToDouble(ScheduleEntry::getMonthlyPayment).sum() + initiationDetails.getDeposit();
        return new ScheduleInfo(initiationDetails, entries.get(0).getMonthlyPayment(), totalInterest, totalPayments, entries);
    }

    public ScheduleInfo saveScheduleInfo(ScheduleInfo scheduleInfo) {
        return scheduleInfoRepository.save(scheduleInfo);
    }

    public ScheduleInfo getScheduleInfo(Long id) {
        return scheduleInfoRepository.findByInitiationDetailsId(id).orElseThrow(() -> new EntityNotFoundException("Schedule information not found"));
    }

    public List<ScheduleInfo> getScheduleInfoList() {
        return scheduleInfoRepository.findAll();
    }
}
