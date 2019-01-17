package com.example.Demo.Controller;

import com.example.Demo.DTO.EventDTO;
import com.example.Demo.Model.Event;
import com.example.Demo.Repository.EventRepository;
import com.example.Demo.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    public EventService eventService;
    @Autowired
    public EventRepository eventRepository;

    @GetMapping
    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<List<EventDTO>> findAll(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping(value="/{id}")
    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity addEvent(@RequestBody @Valid EventDTO eventDTO, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(eventService.addEvent(eventDTO,id));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity getEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity updateEvent(@RequestBody @Valid EventDTO eventDTO, @PathVariable Long id){
        return ResponseEntity.ok(eventService.updateEvent(eventDTO,id));

    }

    @GetMapping(value = "/findAvailable")
    public ResponseEntity<List<EventDTO>> findAvailabile(){
        return ResponseEntity.ok(eventService.getAllAvailable());
    }

    @GetMapping(value = "/findUnavailable")
    public ResponseEntity<List<EventDTO>> findUnavailable(){
        return ResponseEntity.ok(eventService.getAllUnavailable());
    }

    @GetMapping(value = "/findMyEvent/{id}")
    @PreAuthorize("(hasRole('ROLE_ORGNIZER'))")
    public ResponseEntity<List<EventDTO>> getMyEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getMyEvent(id));
    }

    @PutMapping(value = "/approve/{id}")
    public void approveEvent(@PathVariable Long id){
        eventService.ApproveEvent(id);
    }

    @PutMapping(value = "/disapprove/{id}")
    public void disapproveEvent(@PathVariable Long id){
        eventService.DisapproveEvent(id);
    }

    @GetMapping(value = "/findbycity/{city}")
    public ResponseEntity<List<EventDTO>> getByCity(@PathVariable String city){
        return ResponseEntity.ok(eventService.getByCity(city));
    }

    @GetMapping(value = "/findbydate/{date}")
    public ResponseEntity<List<EventDTO>> getByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate date){
        return ResponseEntity.ok(eventService.getByDate(date));
    }

    @GetMapping(value = "/findboth/{city}/{date}")
    public ResponseEntity<List<EventDTO>> getByCityAndDate(@PathVariable String city,@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate date){
        return ResponseEntity.ok(eventService.getBoth(city,date));
    }

    @DeleteMapping(value="/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }

    @DeleteMapping(value="/delete")
    public void deleteAllEvents(){
        eventService.deleteAll();
    }


}


