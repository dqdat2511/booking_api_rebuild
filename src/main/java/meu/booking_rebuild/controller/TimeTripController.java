package meu.booking_rebuild.controller;

import meu.booking_rebuild.dto.TimeDto;
import meu.booking_rebuild.model.TimeTripModel;
import meu.booking_rebuild.repository.TimeTripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(path = "/time",  produces = MediaType.APPLICATION_JSON_VALUE)

public class TimeTripController {
    @Autowired
    private TimeTripRepo repo;
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addTimeTrip(@RequestBody TimeDto model){
        try{
            LocalDate currentDate = LocalDate.now();
            LocalDate specificDateStart = model.getStart_day().toLocalDate();
            LocalDate specificDateEnd = model.getEnd_day().toLocalDate();
            if(currentDate.isAfter(specificDateStart) || currentDate.isAfter(specificDateEnd) || specificDateStart.isAfter(specificDateEnd)){
                return new ResponseEntity<>("The invalid day", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            TimeTripModel model1 = new TimeTripModel();
            model1.setStart_time(model.getStart_time());
            model1.setEnd_time(model.getEnd_time());
            model1.setStart_day(model.getStart_day());
            model1.setEnd_day(model.getEnd_day());
            repo.save(model1);
        }catch (RuntimeException e){
            return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getTime(){
        try{
            LocalDate currentDate = LocalDate.now();
            List<TimeTripModel> timeTrips = repo.findByStartDayGreaterThanEqualAndEndDayGreaterThanEqual(currentDate, currentDate);
            return new ResponseEntity<>(timeTrips, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
