package meu.booking_rebuild.controller;

import meu.booking_rebuild.dto.BusTypeDto;
import meu.booking_rebuild.dto.TimeDto;
import meu.booking_rebuild.model.TimeTripModel;
import meu.booking_rebuild.model.TripModel;
import meu.booking_rebuild.repository.TripRepo;
import meu.booking_rebuild.response.TripResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
@Controller
@RequestMapping("api/v1/trip")
public class TripController {
    private Date currentDate = Date.valueOf(LocalDate.now());
    private LocalTime temp = LocalTime.now();
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
    @ResponseBody
    public ResponseEntity<?> getAllTrip(){
        try{
            List<TripModel> models = repo.findAll();
            ArrayList<TripResponse> tripResponses = new ArrayList<>();
            for(TripModel model: models){
                boolean finished = false;
                String name = model.getName();
                Date start_day = model.getTimetrip().getStart_day();
                Date end_day = model.getTimetrip().getEnd_day();
                Time start_time = model.getTimetrip().getStart_time();
                Time end_time = model.getTimetrip().getEnd_time();
                Integer number_seats = model.getType().getMaxslot();
                if((start_day.equals(end_day) && (temp.isAfter(end_time.toLocalTime())) &&  (currentDate.equals(end_day))|| (currentDate.after(end_day))) || (currentDate.equals(end_day)) && (temp.isAfter(end_time.toLocalTime()))){
                    finished = true;
                }
                BusTypeDto typeBus = new BusTypeDto(model.getType().getName(),model.getType().getMaxslot(),model.getType().getNumbers_floor(),model.getType().getConvenients());
                TimeDto timeTrip = new TimeDto(start_time,end_time,start_day, end_day);
                TripResponse response = new TripResponse(model.getId(),name, timeTrip, typeBus, finished);
                tripResponses.add(response);
            }
            return new ResponseEntity<>(tripResponses, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("detail")
    @ResponseBody
    public ResponseEntity<?> getTrip(@RequestParam UUID id){
        boolean finished = false;
        try{
            TripModel model = repo.findTripModelById(id);
            String name = model.getName();

            Date start_day = model.getTimetrip().getStart_day();
            Date end_day = model.getTimetrip().getEnd_day();
            Time start_time = model.getTimetrip().getStart_time();
            Time end_time = model.getTimetrip().getEnd_time();
            Integer number_seats = model.getType().getMaxslot();

            if((start_day.equals(end_day) && (temp.isAfter(end_time.toLocalTime())) &&  (currentDate.equals(end_day))|| (currentDate.after(end_day))) || (currentDate.equals(end_day)) && (temp.isAfter(end_time.toLocalTime()))){
                finished = true;
            }
            LocalTime temp_time = end_time.toLocalTime();
            BusTypeDto typeBus = new BusTypeDto(model.getType().getName(),model.getType().getMaxslot(),model.getType().getNumbers_floor(),model.getType().getConvenients());
            TimeDto timeTrip = new TimeDto(start_time,end_time,start_day, end_day);
            TripResponse response = new TripResponse(model.getId(),name, timeTrip, typeBus, finished);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    private static int convertToSeconds(LocalTime localTime) {
        int seconds = localTime.getHour() * 3600 + localTime.getMinute() * 60 + localTime.getSecond();
        return seconds;
    }
}
