package com.ldms.benjones.service;

import com.ldms.benjones.utils.ScheduleInfo;
import com.ldms.benjones.entity.Schedule;
import com.ldms.benjones.entity.ScheduleEntry;
import com.ldms.benjones.repository.ScheduleEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleEntryService {

    private final ScheduleEntryRepository scheduleEntryRepository;

    @Autowired
    public ScheduleEntryService (ScheduleEntryRepository scheduleEntryRepository) {
        this.scheduleEntryRepository = scheduleEntryRepository;
    }

    public List<ScheduleEntry> saveScheduleEntries(List<ScheduleEntry> scheduleEntries) {
        return scheduleEntryRepository.saveAll(scheduleEntries);
    }

    public List<ScheduleEntry> getScheduleEntriesForSchedule(Long scheduleId) {
        return scheduleEntryRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("No entries found"));
    }

    public List<ScheduleEntry> generateEntries(Schedule schedule) {
        double repaymentAmount = calculateMonthlyRepayment(schedule);
        double monthlyInterest = schedule.getYearlyInterest() / 12;
        List<ScheduleEntry> entries = new ArrayList<>();
        double remainingBalance;
        for (int i = 1; i <= schedule.getNumberOfRepayments(); i++) {
            if (i == 1) {
                // It's the first payment so total balance
                remainingBalance = (schedule.getAmountBorrowed() - schedule.getDeposit());
            } else {
                remainingBalance = entries.get(i-2).getRemainingBalance();
            }
            if (i == schedule.getNumberOfRepayments()) {
                repaymentAmount = remainingBalance;
                monthlyInterest = 0;
            }
            ScheduleEntry entry = calculateScheduleEntry(monthlyInterest, remainingBalance, repaymentAmount, schedule.getId(), i);
            entries.add(entry);
        }
        return entries;
    }

    public double calculateMonthlyRepayment(Schedule schedule) {
        double monthlyInterest = schedule.getYearlyInterest() / 12;
        double remainingBalance = schedule.getAmountBorrowed() - schedule.getDeposit();
        double subtractionFromRemBal = schedule.getBalloonPayment() / (Math.pow(1+monthlyInterest, schedule.getNumberOfRepayments()));
        double subtractFromOne = Math.pow((1 + monthlyInterest), (schedule.getNumberOfRepayments() * -1));
        double multiplicationFactor = monthlyInterest / (1 - subtractFromOne);
        return (remainingBalance - subtractionFromRemBal) * multiplicationFactor;
    }

    public ScheduleEntry calculateScheduleEntry(double monthlyInterest, double remainingBalance, double monthlyPayment, Long scheduleId, int paymentNumber) {
        double interestPayment = remainingBalance * monthlyInterest;
        double principalPayment = monthlyPayment - interestPayment;
        double balance = remainingBalance - principalPayment;
        return new ScheduleEntry(scheduleId, paymentNumber, monthlyPayment, principalPayment, interestPayment, balance);
    }

    public List<ScheduleInfo> getScheduleInfoList(List<Schedule> schedules) {
        List<ScheduleInfo> infoList = new ArrayList<>();

        schedules.forEach(schedule -> {
            ScheduleInfo info = getScheduleInfo(schedule);
            infoList.add(info);
        });

        return infoList;
    }

    public ScheduleInfo getScheduleInfo(Schedule schedule) {
        List<ScheduleEntry> entries = getScheduleEntriesForSchedule(schedule.getId());
        double totalInterest = entries.stream().mapToDouble(ScheduleEntry::getInterestPayment).sum();
        double totalPayments = entries.stream().mapToDouble(ScheduleEntry::getMonthlyPayment).sum() + schedule.getDeposit();
        return new ScheduleInfo(schedule.getAmountBorrowed(), schedule.getDeposit(), schedule.getYearlyInterest(), schedule.getNumberOfRepayments(),
                schedule.getBalloonPayment(), entries.get(0).getMonthlyPayment(), totalInterest, totalPayments, entries);
    }
}
