package com.ldms.benjones.repository;

import com.ldms.benjones.entity.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, Long> {
    Optional<ScheduleInfo> findByInitiationDetailsId(Long id);
}
