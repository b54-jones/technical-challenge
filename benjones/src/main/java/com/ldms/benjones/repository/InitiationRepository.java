package com.ldms.benjones.repository;

import com.ldms.benjones.entity.InitiationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiationRepository extends JpaRepository<InitiationDetails, Long> {

}
