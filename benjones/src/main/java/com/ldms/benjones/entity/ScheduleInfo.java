package com.ldms.benjones.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ldms.benjones.utils.View;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="schedule_info")
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(View.Summary.class)
    @OneToOne
    @JoinColumn(name="initiation_details_id", referencedColumnName = "id")
    private InitiationDetails initiationDetails;

    @JsonView(View.Summary.class)
    @Column
    private double monthlyRepaymentAmount;

    @JsonView(View.Summary.class)
    @Column
    private double totalInterest;

    @JsonView(View.Summary.class)
    @Column
    private double totalPayments;

    @JsonView(View.Detail.class)
    @OneToMany(mappedBy = "initiationId")
    List<ScheduleEntry> scheduleEntryList;

    public ScheduleInfo() {
    }

    public ScheduleInfo(InitiationDetails initiationDetails, double monthlyRepaymentAmount, double totalInterest, double totalPayments, List<ScheduleEntry> scheduleEntryList) {
        this.initiationDetails = initiationDetails;
        this.monthlyRepaymentAmount = monthlyRepaymentAmount;
        this.totalInterest = totalInterest;
        this.totalPayments = totalPayments;
        this.scheduleEntryList = scheduleEntryList;
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

    public List<ScheduleEntry> getScheduleEntryList() {
        return scheduleEntryList;
    }

    public void setScheduleEntryList(List<ScheduleEntry> scheduleEntryList) {
        this.scheduleEntryList = scheduleEntryList;
    }
}
