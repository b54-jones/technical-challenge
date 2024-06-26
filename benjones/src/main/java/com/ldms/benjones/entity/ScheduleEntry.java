package com.ldms.benjones.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ldms.benjones.utils.View;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule_entry")
@JsonView(View.Detail.class)
public class ScheduleEntry {

    public ScheduleEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInitiationId() {
        return initiationId;
    }

    public void setInitiationId(Long initiationId) {
        this.initiationId = initiationId;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(double principalPayment) {
        this.principalPayment = principalPayment;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(double interestPayment) {
        this.interestPayment = interestPayment;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public ScheduleEntry(Long initiationId, int paymentNumber, double monthlyPayment, double principalPayment, double interestPayment, double remainingBalance) {
        this.initiationId = initiationId;
        this.paymentNumber = paymentNumber;
        this.monthlyPayment = monthlyPayment;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.remainingBalance = remainingBalance;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private Long initiationId;

    @Column
    private int paymentNumber;

    @Column
    private double monthlyPayment;

    @Column
    private double principalPayment;

    @Column
    private double interestPayment;

    @Column
    private double remainingBalance;
}
