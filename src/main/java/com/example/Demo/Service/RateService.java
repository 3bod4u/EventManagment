package com.example.Demo.Service;

import com.example.Demo.DTO.RateDTO;
import com.example.Demo.Model.Rate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RateService {

    RateDTO addRate(RateDTO rateDTO, Long uid, Long eid);
    List<RateDTO> getAllRates();
    RateDTO getRate(Long id);
    RateDTO updateRate(RateDTO rateDTO,Long id);
    void deleteRate(Long id);
    void deleteAll();
}
