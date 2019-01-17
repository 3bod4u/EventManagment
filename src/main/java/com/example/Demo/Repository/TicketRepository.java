package com.example.Demo.Repository;

import com.example.Demo.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByAvailabilityIsTrue();
    List<Ticket> findAllByAvailabilityIsTrueAndEvent_Eventid(Long id);
    List<Ticket> findAllByUserUseridAndAvailabilityIsTrue(Long id);
    Ticket findByUserUseridAndEvent_EventidAndAvailabilityIsTrue(Long userid,Long eventid);
    Long countAllByEvent_EventidAndAvailabilityIsTrue(Long id);


}
