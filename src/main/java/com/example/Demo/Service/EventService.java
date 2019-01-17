package com.example.Demo.Service;

import com.example.Demo.DTO.EventDTO;
import com.example.Demo.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {

    List<EventDTO> getAllEvents();
    EventDTO addEvent(EventDTO eventDTO, Long id);
    EventDTO getEvent(Long id);
    EventDTO updateEvent(EventDTO eventDTO,Long id);
    List<EventDTO> getAllAvailable();
    List<EventDTO> getAllUnavailable();
    List<EventDTO> getMyEvent(Long id);
    List<EventDTO> getByCity(String city);
    List<EventDTO> getByDate(LocalDate date);
    List<EventDTO> getBoth(String city, LocalDate date);
    void ApproveEvent(Long id);
    void DisapproveEvent(Long id);
    void deleteEvent(Long id);
    void deleteAll();

}
