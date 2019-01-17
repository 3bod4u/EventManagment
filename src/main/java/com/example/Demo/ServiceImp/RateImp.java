package com.example.Demo.ServiceImp;

import com.example.Demo.DTO.RateDTO;
import com.example.Demo.Model.Rate;
import com.example.Demo.Model.Ticket;
import com.example.Demo.Repository.EventRepository;
import com.example.Demo.Repository.RateRepository;
import com.example.Demo.Repository.TicketRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Service.RateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RateImp implements RateService {

    @Autowired
    public RateRepository rateRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public TicketRepository ticketRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<RateDTO> getAllRates() {
        List<Rate> rateEntitys = rateRepository.findAll();
        List<RateDTO> rateDTOs = new ArrayList<>();
        for(Rate rate : rateEntitys){
            RateDTO rateDTO = modelMapper.map(rate,RateDTO.class);
            rateDTOs.add(rateDTO);
        }
        return rateDTOs;
    }

    @Override
    public RateDTO addRate(RateDTO rateDTO, Long userid, Long eventid) {
        Rate rate = rateRepository.getByAvailabilityIsTrueAndUserUseridAndEvent_Eventid(userid,eventid);
        if(rate!=null){
            return null;
        }
        rate = modelMapper.map(rateDTO,Rate.class);
        rate.setUser(userRepository.findById(userid).get());
        rate.setEvent(eventRepository.findById(eventid).get());
        Ticket ticket = ticketRepository.findByUserUseridAndEvent_EventidAndAvailabilityIsTrue(userid,eventid);
        if(ticket == null){
            return null;
        }
            if (ticket.getEvent().getDate().isBefore(LocalDate.now())) {
                rateRepository.save(rate);
                return rateDTO;
            }
        return null;
    }

    @Override
    public RateDTO getRate(Long id){
        Rate rate = rateRepository.findById(id).get();
        RateDTO rateDTO = modelMapper.map(rate,RateDTO.class);
         return rateDTO;
    }

    @Override
    public RateDTO updateRate(RateDTO rateDTO,Long id) {
        Rate rate = rateRepository.findById(id).get();
        Rate rate1 = modelMapper.map(rateDTO,Rate.class);
        rate1.setRateid(rate.getRateid());
        rate1.setAvailability(rate.isAvailability());
        rateRepository.save(rate1);
        return rateDTO;
    }

    @Override
    public void deleteRate(Long id) {
        Rate rate = rateRepository.findById(id).get();
        rate.setAvailability(false);
        rateRepository.save(rate);
    }

    @Override
    public void deleteAll(){
        rateRepository.deleteAll();
        rateRepository.flush();
    }
}
