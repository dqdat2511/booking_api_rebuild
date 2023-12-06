package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.TimeTripModel;
import meu.booking_rebuild.repository.TimeTripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/time")
public class TimeTripController {
    @Autowired
    private TimeTripRepo repo;
    @PostMapping
    public ResponseEntity<?> addTime(@RequestBody TimeTripModel model){
        try{
            repo.save(model);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
