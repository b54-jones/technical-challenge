package com.ldms.benjones.repository;

import com.ldms.benjones.entity.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Long> {
    Optional<ScheduleEntry> findByScheduleIdAndPaymentNumber(Long scheduleId, int paymentNumber);

    Optional<List<ScheduleEntry>> findByScheduleId(Long scheduleId);
}
