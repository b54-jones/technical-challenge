package com.ldms.benjones.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.service.ScheduleInfoService;
import com.ldms.benjones.utils.View;
import com.ldms.benjones.entity.InitiationDetails;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.service.ScheduleEntryService;
import com.ldms.benjones.service.InitiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {

    private final InitiationService initiationService;
    private final ScheduleEntryService scheduleEntryService;
    private final ScheduleInfoService scheduleInfoService;

    @Autowired
    public ScheduleController (InitiationService initiationService, ScheduleEntryService scheduleEntryService, ScheduleInfoService scheduleInfoService) {
        this.initiationService = initiationService;
        this.scheduleEntryService = scheduleEntryService;
        this.scheduleInfoService = scheduleInfoService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<InitiationDetails> createSchedule(@RequestBody InitiationDetails details) {
        InitiationDetails savedDetails = initiationService.processAndSaveDetails(details);
        return ResponseEntity.ok(savedDetails);
    }

    @GetMapping("/schedules")
    @JsonView(View.Summary.class)
    public List<ScheduleInfo> getAllSchedules() {
        return scheduleInfoService.getScheduleInfoList();
    }

    @GetMapping("/schedules/{id}")
    @JsonView(View.Detail.class)
    private ScheduleInfo getScheduleInfo(@PathVariable Long id) {
        return scheduleInfoService.getScheduleInfo(id);
    }
}
