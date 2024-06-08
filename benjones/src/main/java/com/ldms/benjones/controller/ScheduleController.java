package com.ldms.benjones.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ldms.benjones.utils.ScheduleInfo;
import com.ldms.benjones.utils.View;
import com.ldms.benjones.entity.Schedule;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.service.ScheduleEntryService;
import com.ldms.benjones.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleEntryService scheduleEntryService;

    @Autowired
    public ScheduleController (ScheduleService scheduleService, ScheduleEntryService scheduleEntryService) {
        this.scheduleService=scheduleService;
        this.scheduleEntryService = scheduleEntryService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        List<ScheduleEntry> scheduleEntries = scheduleEntryService.generateEntries(schedule);
        scheduleEntryService.saveScheduleEntries(scheduleEntries);
        return ResponseEntity.ok(savedSchedule);
    }

    @GetMapping("/schedules")
    @JsonView(View.Summary.class)
    public List<ScheduleInfo> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return scheduleEntryService.getScheduleInfoList(schedules);
    }

    @GetMapping("/schedules/{id}")
    @JsonView(View.Detail.class)
    private ScheduleInfo getScheduleInfo(@PathVariable Long id) {
        Schedule schedule = scheduleService.findSchedule(id);
        return scheduleEntryService.getScheduleInfo(schedule);
    }
}
