package com.ldms.benjones.service;

import com.ldms.benjones.entity.ScheduleInfo;
import com.ldms.benjones.entity.InitiationDetails;
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

    public List<ScheduleEntry> generateEntries(InitiationDetails details) {
        double repaymentAmount = calculateMonthlyRepayment(details);
        double monthlyInterest = details.getYearlyInterest() / 12;
        List<ScheduleEntry> entries = new ArrayList<>();
        double remainingBalance;
        for (int i = 1; i <= details.getNumberOfRepayments(); i++) {
            if (i == 1) {
                // It's the first payment so total balance
                remainingBalance = (details.getAmountBorrowed() - details.getDeposit());
            } else {
                remainingBalance = entries.get(i-2).getRemainingBalance();
            }
            if (i == details.getNumberOfRepayments()) {
                repaymentAmount = remainingBalance;
                monthlyInterest = 0;
            }
            ScheduleEntry entry = calculateScheduleEntry(monthlyInterest, remainingBalance, repaymentAmount, details.getId(), i);
            entries.add(entry);
        }
        return entries;
    }

    public double calculateMonthlyRepayment(InitiationDetails details) {
        double monthlyInterest = details.getYearlyInterest() / 12;
        double remainingBalance = details.getAmountBorrowed() - details.getDeposit();
        double subtractionFromRemBal = details.getBalloonPayment() / (Math.pow(1+monthlyInterest, details.getNumberOfRepayments()));
        double subtractFromOne = Math.pow((1 + monthlyInterest), (details.getNumberOfRepayments() * -1));
        double multiplicationFactor = monthlyInterest / (1 - subtractFromOne);
        return (remainingBalance - subtractionFromRemBal) * multiplicationFactor;
    }

    public ScheduleEntry calculateScheduleEntry(double monthlyInterest, double remainingBalance, double monthlyPayment, Long scheduleId, int paymentNumber) {
        double interestPayment = remainingBalance * monthlyInterest;
        double principalPayment = monthlyPayment - interestPayment;
        double balance = remainingBalance - principalPayment;
        return new ScheduleEntry(scheduleId, paymentNumber, monthlyPayment, principalPayment, interestPayment, balance);
    }
}
