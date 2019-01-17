package com.example.Demo.Controller;

import com.example.Demo.DTO.TicketDTO;
import com.example.Demo.Model.Ticket;
import com.example.Demo.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    public TicketService ticketService;

    @GetMapping
    //@PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<List<TicketDTO>> findAll(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/findAllAvailable")
   // @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity<List<TicketDTO>> findAllAvailable(){
        return ResponseEntity.ok(ticketService.getAllTicketsAvailable());
    }

    @GetMapping(value="/{id}")
   // @PreAuthorize("(hasAnyRole('ADMIN','ATTENDER'))")
    public ResponseEntity getTicket(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping("/user/{id}")
   // @PreAuthorize("(hasAnyRole('ADMIN','ATTENDER'))")
    public ResponseEntity<List<TicketDTO>> getUserTickets(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.showUserTicket(id));
    }

    @PostMapping(value="/{eventId}/{userId}")
//    @PreAuthorize("(hasRole('ADMIN'))")
    public ResponseEntity addTicket(@RequestBody @Valid TicketDTO ticketDTO, @PathVariable Long eventId , @PathVariable Long userId, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok(ticketService.addTicket(ticketDTO,eventId,userId));
    }

    @DeleteMapping(value="/{id}")
    @PreAuthorize("(hasAnyRole('ADMIN','ATTENDER'))")
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

    @DeleteMapping(value="/delete")
   // @PreAuthorize("(hasRole('ADMIN'))")
    public void deleteAllTicket(){
        ticketService.deleteAll();
    }
}
