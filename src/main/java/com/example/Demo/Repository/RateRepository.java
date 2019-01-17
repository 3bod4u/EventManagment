package com.example.Demo.Repository;

import com.example.Demo.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
    Rate getByAvailabilityIsTrueAndUserUseridAndEvent_Eventid(Long uid, Long eid);
}
