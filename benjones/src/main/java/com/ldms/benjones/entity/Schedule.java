package com.ldms.benjones.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(double amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    public double getYearlyInterest() {
        return yearlyInterest;
    }

    public void setYearlyInterest(double yearlyInterest) {
        this.yearlyInterest = yearlyInterest;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalloonPayment() {
        return balloonPayment;
    }

    public void setBalloonPayment(double balloonPayment) {
        this.balloonPayment = balloonPayment;
    }

    public int getNumberOfRepayments() {
        return numberOfRepayments;
    }

    public void setNumberOfRepayments(int numberOfRepayments) {
        this.numberOfRepayments = numberOfRepayments;
    }

    public Schedule(Long id, double amountBorrowed, double yearlyInterest, double deposit, double balloonPayment, int numberOfRepayments) {
        this.id = id;
        this.amountBorrowed = amountBorrowed;
        this.yearlyInterest = yearlyInterest;
        this.deposit = deposit;
        this.balloonPayment = balloonPayment;
        this.numberOfRepayments = numberOfRepayments;
    }

    public Schedule() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private double amountBorrowed;

    @Column(nullable = false)
    private double yearlyInterest;

    @Column(nullable = false)
    private double deposit;

    @Column(nullable = false)
    private double balloonPayment = 0;

    @Column(nullable = false)
    private int numberOfRepayments;
}
