package com.ldms.benjones.utils;

import com.fasterxml.jackson.annotation.JsonView;
import com.ldms.benjones.entity.ScheduleEntry;

import java.util.List;

public class ScheduleInfo {
    @JsonView(View.Summary.class)
    private double amountBorrowed;
    @JsonView(View.Summary.class)
    private double deposit;

    public double getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(double amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getYearlyInterest() {
        return yearlyInterest;
    }

    public void setYearlyInterest(double yearlyInterest) {
        this.yearlyInterest = yearlyInterest;
    }

    public int getNumberOfRepayments() {
        return numberOfRepayments;
    }

    public void setNumberOfRepayments(int numberOfRepayments) {
        this.numberOfRepayments = numberOfRepayments;
    }

    public double getBalloonPayment() {
        return balloonPayment;
    }

    public void setBalloonPayment(double balloonPayment) {
        this.balloonPayment = balloonPayment;
    }

    public double getMonthlyRepaymentAmount() {
        return monthlyRepaymentAmount;
    }

    public void setMonthlyRepaymentAmount(double monthlyRepaymentAmount) {
        this.monthlyRepaymentAmount = monthlyRepaymentAmount;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(double totalPayments) {
        this.totalPayments = totalPayments;
    }

    @JsonView(View.Summary.class)
    private double yearlyInterest;

    @JsonView(View.Summary.class)
    private int numberOfRepayments;

    @JsonView(View.Summary.class)
    private double balloonPayment;

    @JsonView(View.Summary.class)
    private double monthlyRepaymentAmount;

    @JsonView(View.Summary.class)
    private double totalInterest;

    public ScheduleInfo() {
    }

    public ScheduleInfo(double amountBorrowed, double deposit, double yearlyInterest, int numberOfRepayments, double balloonPayment, double monthlyRepaymentAmount, double totalInterest, double totalPayments, List<ScheduleEntry> scheduleEntryList) {
        this.amountBorrowed = amountBorrowed;
        this.deposit = deposit;
        this.yearlyInterest = yearlyInterest;
        this.numberOfRepayments = numberOfRepayments;
        this.balloonPayment = balloonPayment;
        this.monthlyRepaymentAmount = monthlyRepaymentAmount;
        this.totalInterest = totalInterest;
        this.totalPayments = totalPayments;
        this.scheduleEntryList = scheduleEntryList;
    }

    @JsonView(View.Summary.class)
    private double totalPayments;

    public List<ScheduleEntry> getScheduleEntryList() {
        return scheduleEntryList;
    }

    public void setScheduleEntryList(List<ScheduleEntry> scheduleEntryList) {
        this.scheduleEntryList = scheduleEntryList;
    }

    @JsonView(View.Detail.class)
    List<ScheduleEntry> scheduleEntryList;
}
