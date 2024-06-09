package com.ldms.benjones.service;

import com.ldms.benjones.entity.InitiationDetails;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.repository.InitiationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitiationService {
    private final InitiationRepository initiationRepository;
    private final ScheduleEntryService scheduleEntryService;
    private final ScheduleInfoService scheduleInfoService;

    @Autowired
    public InitiationService(InitiationRepository initiationRepository, ScheduleEntryService scheduleEntryService, ScheduleInfoService scheduleInfoService) {
        this.initiationRepository = initiationRepository;
        this.scheduleEntryService = scheduleEntryService;
        this.scheduleInfoService = scheduleInfoService;
    }
    public InitiationDetails processAndSaveDetails(InitiationDetails details) {
        InitiationDetails savedDetails = saveInitiationDetails(details);
        List<ScheduleEntry> scheduleEntries = scheduleEntryService.generateEntries(savedDetails);
        ScheduleInfo scheduleInfo = scheduleInfoService.generateScheduleInfo(details, scheduleEntries);
        scheduleInfoService.saveScheduleInfo(scheduleInfo);
        scheduleEntryService.saveScheduleEntries(scheduleEntries);
        return initiationRepository.save(details);
    }

    public InitiationDetails saveInitiationDetails(InitiationDetails details) {
        return initiationRepository.save(details);
    }
}
