package com.example.Demo.Controller;

import com.example.Demo.DTO.RateDTO;
import com.example.Demo.Model.Rate;
import com.example.Demo.Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    public RateService rateService;

    @GetMapping
    public ResponseEntity<List<RateDTO>> findAll(){
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @PostMapping(value="/{uid}/{eid}")
    public ResponseEntity addRate(@RequestBody @Valid RateDTO rateDTO, @PathVariable Long uid , @PathVariable Long eid, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(rateService.addRate(rateDTO,uid,eid));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity getRate(@PathVariable Long id){
        return ResponseEntity.ok(rateService.getRate(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateRate(@RequestBody @Valid RateDTO rateDTO,@PathVariable Long id){
      return ResponseEntity.ok(rateService.updateRate(rateDTO,id));

    }

    @DeleteMapping(value="/{id}")
    public void deleteRate(@PathVariable Long id){
        rateService.deleteRate(id);
    }

    @DeleteMapping(value="/delete")
    public void deleteAllRate(){
        rateService.deleteAll();
    }

}
