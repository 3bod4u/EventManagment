package com.example.Demo.Repository;

import com.example.Demo.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByCityAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(String city,LocalDate date);
    List<Event> findByDateAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(LocalDate date,LocalDate date2);
    List<Event> findByCityAndDateAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(String city,LocalDate date,LocalDate date2);
    List<Event> findAllByAvailabilityIsTrueAndApprovedIsTrueAndDateIsAfter(LocalDate date);
    List<Event> findAllByUserUserid(Long id);
    List<Event> findAllByApprovedIsFalseAndAvailabilityIsTrueAndDateIsAfter(LocalDate date);


}
