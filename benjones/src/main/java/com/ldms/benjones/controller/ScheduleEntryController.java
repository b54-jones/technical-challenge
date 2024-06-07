package com.ldms.benjones.controller;

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
public class ScheduleEntryController {

    private final ScheduleEntryService scheduleEntryService;

    @Autowired
    public ScheduleEntryController(ScheduleEntryService scheduleEntryService) {
        this.scheduleEntryService = scheduleEntryService;
    }

    @GetMapping("/schedule-entries/{id}")
    public List<ScheduleEntry> saveSchedule(@PathVariable String id) {
        return scheduleEntryService.getScheduleEntriesForSchedule(Long.valueOf(id));
    }
}
