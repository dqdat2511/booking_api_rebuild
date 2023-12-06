package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.TimeTripModel;
import meu.booking_rebuild.model.TripModel;
import meu.booking_rebuild.repository.TripRepo;
import meu.booking_rebuild.response.TripResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Controller
@RequestMapping("api/v1/trip")
public class TripController {
    @Autowired
    private TripRepo repo;
    @DeleteMapping
    public ResponseEntity<?> deleteTrip(@RequestParam UUID id){
        try{
            TripModel trip = repo.findTripModelById(id);
            repo.delete(trip);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getTrip(@RequestParam UUID id){

        try{
            TripModel model = repo.findTripModelById(id);
            String name = model.getName();
            Date start_day = model.getTimetrip().getStart_day();
            Date end_day = model.getTimetrip().getEnd_day();
            Time start_time = model.getTimetrip().getStart_time();
            Time end_time = model.getTimetrip().getEnd_time();
            Integer number_seats = model.getType().getMaxslot();
            TripResponse response = new TripResponse(name, start_day, end_day, start_time, end_time, number_seats);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
