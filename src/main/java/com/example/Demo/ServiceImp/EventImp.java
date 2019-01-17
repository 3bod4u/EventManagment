package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.EventDTO;
import com.example.Demo.Model.Event;
import com.example.Demo.Model.Ticket;
import com.example.Demo.Model.User;
import com.example.Demo.Repository.EventRepository;
import com.example.Demo.Repository.TicketRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.invoke.empty.Empty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventImp implements EventService {

    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public TicketRepository ticketRepository;
    @Autowired
    public NotificationServiceImp notificationServiceImp;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public EventDTO addEvent(EventDTO eventDTO, Long id) {
        if(eventDTO.getDate().isAfter(LocalDate.now())){
            Event event = modelMapper.map(eventDTO,Event.class);
            event.setUser(userRepository.findById(id).get());
            eventRepository.save(event);
            return eventDTO;
        }
        return null;

    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventsDTO = new ArrayList<>();
        for(Event event : events){
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            eventsDTO.add(eventDTO);
        }
        return eventsDTO;
    }

    @Override
    public EventDTO getEvent(Long id){
        EventDTO eventDTO = modelMapper.map(eventRepository.findById(id).get(),EventDTO.class);
        return eventDTO;
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO, Long id) {
        Event event1 = eventRepository.findById(id).get();
        if(eventDTO.getDate().isBefore(LocalDate.now())||eventDTO.getDate().isEqual(LocalDate.now())|| eventDTO.getSeats()< event1.getSeats()){
            return null;
        }else {
            Event event2 = modelMapper.map(eventDTO, Event.class);
            event2.setEventid(event1.getEventid());
            event2.setApproved(event1.isApproved());
            event2.setAvailability(event1.isAvailability());
            event2.setSeats(event1.getSeats());
            event2.setUser(event1.getUser());
            List<Ticket> tickets = ticketRepository.findAllByAvailabilityIsTrueAndEvent_Eventid(id);
            for (Ticket ticket : tickets) {
                notificationServiceImp.sendNotificaitoin(ticket.getUser(), "Event Updated", "Event Updated");
            }
            ResponseEntity.ok(eventRepository.save(event2));
            return eventDTO;
        }
    }

    @Override
    public List<EventDTO> getAllAvailable() {
        List<Event> list = eventRepository.findAllByAvailabilityIsTrueAndApprovedIsTrueAndDateIsAfter(LocalDate.now());
        List<EventDTO> list1 = new ArrayList<>();
        for(Event event : list){
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            list1.add(eventDTO);
        }
        return list1;
    }

    @Override
    public List<EventDTO> getAllUnavailable() {
        List<Event> list = eventRepository.findAllByApprovedIsFalseAndAvailabilityIsTrueAndDateIsAfter(LocalDate.now());
        List<EventDTO> list1 = new ArrayList<>();
        for(Event event : list){
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            list1.add(eventDTO);
        }
        return list1;
    }

    @Override
    public List<EventDTO> getMyEvent(Long id) {
        List<Event> list = eventRepository.findAllByUserUserid(id);
        List<EventDTO> list1 = new ArrayList<>();
        for(Event event : list){
            event.setCounter(ticketRepository.countAllByEvent_EventidAndAvailabilityIsTrue(event.getEventid()));
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            list1.add(eventDTO);
        }
        return list1;
    }

    @Override
    public List<EventDTO> getByCity(String city) {
        List<Event> list = eventRepository.findByCityAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(city,LocalDate.now());
        List<EventDTO> list1 = new ArrayList<>();
        for(Event event : list) {
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            list1.add(eventDTO);
        }
     return list1;

    }

    @Override
    public List<EventDTO> getByDate(LocalDate date) {
        List<Event> list = eventRepository.findByDateAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(date,LocalDate.now());
        List<EventDTO> list1 = new ArrayList<>();
        for(Event event : list){
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            list1.add(eventDTO);
        }
       return list1;
    }

    @Override
    public List<EventDTO> getBoth(String city, LocalDate date) {
        List<Event> events = eventRepository.findByCityAndDateAndApprovedIsTrueAndAvailabilityIsTrueAndDateIsAfter(city,date,LocalDate.now());
        List<EventDTO> eventsDTO = new ArrayList<>();
        for(Event event : events){
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            eventsDTO.add(eventDTO);
        }
        return eventsDTO;
    }

    @Override
    public void ApproveEvent(Long id){
        Event event = eventRepository.findById(id).get();
        event.setApproved(true);
        eventRepository.save(event);
    }

    @Override
    public void DisapproveEvent(Long id) {
        Event event = eventRepository.findById(id).get();
        event.setApproved(false);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).get();
        event.setAvailability(false);
        eventRepository.save(event);
    }

    @Override
    public void deleteAll(){
        eventRepository.deleteAll();
        eventRepository.flush();
    }

}
