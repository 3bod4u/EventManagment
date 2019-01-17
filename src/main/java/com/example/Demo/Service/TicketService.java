package com.example.Demo.Service;

import com.example.Demo.DTO.TicketDTO;
import com.example.Demo.Model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    
    TicketDTO addTicket(TicketDTO ticketDTO, Long eventid , Long userid);
    List<TicketDTO> getAllTickets();
    List<TicketDTO> getAllTicketsAvailable();
    List<TicketDTO> showUserTicket(Long id);
    TicketDTO getTicket(Long id);
    void deleteTicket(Long id);
    void deleteAll();

}
