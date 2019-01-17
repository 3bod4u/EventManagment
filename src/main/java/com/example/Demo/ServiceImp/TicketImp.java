package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.TicketDTO;
import com.example.Demo.Model.Event;
import com.example.Demo.Model.Ticket;
import com.example.Demo.Repository.EventRepository;
import com.example.Demo.Repository.TicketRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TicketImp implements TicketService {

    @Autowired
    public TicketRepository ticketRepository;
    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public TicketDTO addTicket(TicketDTO ticketDTO, Long eventid, Long userid) {
        Ticket ticket = modelMapper.map(ticketDTO,Ticket.class);
        Event event = eventRepository.findById(eventid).get();
        ticket.setEvent(event);
        ticket.setTitle(event.getTitle());
        ticket.setDate(event.getDate());
        ticket.setUser(userRepository.findById(userid).get());

        List<Ticket> tickets = ticketRepository.findAllByUserUseridAndAvailabilityIsTrue(userid);
        if(!(ticket.getEvent().isAvailability())){
            return null;
        }
        for (Ticket ticket1 : tickets) {
            // if he booked the same event
            if (ticket1.getEvent().getEventid() == ticket.getEvent().getEventid()) {
                return null;
            }
            //if he booked in the same date
            if(ticket1.getEvent().getDate().isEqual(ticket.getEvent().getDate())){
                return null;
            }
        }
        // if the seats bigger then the booked
        if (ticket.getEvent().getSeats() > ticketRepository.countAllByEvent_EventidAndAvailabilityIsTrue(eventid)){
            ticket.setTicketPass(getSaltString(ticket.getEvent().getCity()));
            ticketRepository.save(ticket);
            return ticketDTO;
        }else{
            return null;
        }
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> ticketEntitys = ticketRepository.findAll();
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketEntitys){
            TicketDTO ticketDTO = modelMapper.map(ticket,TicketDTO.class);
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @Override
    public List<TicketDTO> getAllTicketsAvailable() {
        List<Ticket> ticketEntitys = ticketRepository.findAllByAvailabilityIsTrue();
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketEntitys){
            TicketDTO ticketDTO = modelMapper.map(ticket,TicketDTO.class);
            ticketDTOS.add(ticketDTO);
        }

        return ticketDTOS;
    }

    @Override
    public List<TicketDTO> showUserTicket(Long id) {
        List<Ticket> ticketEntitys = ticketRepository.findAllByUserUseridAndAvailabilityIsTrue(id);
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for(Ticket ticket : ticketEntitys){
            TicketDTO ticketDTO = modelMapper.map(ticket,TicketDTO.class);
            ticketDTOs.add(ticketDTO);
        }
        return ticketDTOs;
    }

    @Override
    public TicketDTO getTicket(Long id){
        Ticket ticketEntity = ticketRepository.findById(id).get();
        TicketDTO ticketDTO = modelMapper.map(ticketEntity,TicketDTO.class);
        return ticketDTO;
    }

    @Override
    public void deleteTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setAvailability(false);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteAll(){

        ticketRepository.deleteAll();
        ticketRepository.flush();

    }

    protected String getSaltString(String city) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        salt.append(city+"-");
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
