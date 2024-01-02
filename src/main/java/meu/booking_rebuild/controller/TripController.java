package meu.booking_rebuild.controller;

import meu.booking_rebuild.dto.BusTypeDto;
import meu.booking_rebuild.dto.TimeDto;
import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.model.TimeTripModel;
import meu.booking_rebuild.model.TripModel;
import meu.booking_rebuild.repository.BusSlotRepo;
import meu.booking_rebuild.repository.BusTypeRepo;
import meu.booking_rebuild.repository.TripRepo;
import meu.booking_rebuild.request.TripRequest;
import meu.booking_rebuild.response.StatusResponse;
import meu.booking_rebuild.response.TripResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(path = "/trip", produces = MediaType.APPLICATION_JSON_VALUE)
public class TripController {
    private Date currentDate = Date.valueOf(LocalDate.now());
    private LocalTime temp = LocalTime.now();
    @Autowired
    private TripRepo repo;
    @Autowired
    private BusTypeRepo type_repo;
    @Autowired
    private BusSlotRepo slot_repo;
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllTrip(){
        try{
            List<TripModel> models = repo.findAllByOrderByTimeTripStartDayAscTimeTripStartTimeAsc();
            ArrayList<TripResponse> tripResponses = new ArrayList<>();
            for(TripModel model: models){
                boolean finished = false;
                String name = model.getName();
                Date start_day = model.getTimetrip().getStart_day();
                Date end_day = model.getTimetrip().getEnd_day();
                Time start_time = model.getTimetrip().getStart_time();
                Time end_time = model.getTimetrip().getEnd_time();
                if((start_day.equals(end_day) && (temp.isAfter(end_time.toLocalTime())) &&  (currentDate.equals(end_day))|| (currentDate.after(end_day))) || (currentDate.equals(end_day)) && (temp.isAfter(end_time.toLocalTime()))){
                    finished = true;
                    model.set_finished(finished);
                    repo.save(model);
                }
                model.set_finished(finished);
                repo.save(model);
                BusTypeDto typeBus = new BusTypeDto(model.getType().getId(), model.getType().getName(),model.getType().getMaxslot(),model.getType().getNumbers_floor(), model.getType().getNumber_plate(), model.getType().getConvenients());
                TimeDto timeTrip = new TimeDto(model.getTimetrip().getId(), start_time,end_time,start_day, end_day);
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
            if((start_day.equals(end_day) && (temp.isAfter(end_time.toLocalTime())) &&  (currentDate.equals(end_day))|| (currentDate.after(end_day))) || (currentDate.equals(end_day)) && (temp.isAfter(end_time.toLocalTime()))){
                finished = true;
            }
            BusTypeDto typeBus = new BusTypeDto(model.getType().getId(), model.getType().getName(),model.getType().getMaxslot(),model.getType().getNumbers_floor(), model.getType().getNumber_plate(), model.getType().getConvenients());
            TimeDto timeTrip = new TimeDto(model.getTimetrip().getId(), start_time,end_time,start_day, end_day);
            TripResponse response = new TripResponse(model.getId(),name, timeTrip, typeBus, finished);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("ready")
    @ResponseBody
    public ResponseEntity<?> getTripToReady(){
        try{
            List<TripModel> models = repo.findTripModelsBy_finishedEquals(false);
            ArrayList<TripResponse> tripResponses = new ArrayList<>();
            for(TripModel model: models){
                boolean finished = false;
                String name = model.getName();
                Date start_day = model.getTimetrip().getStart_day();
                Date end_day = model.getTimetrip().getEnd_day();
                Time start_time = model.getTimetrip().getStart_time();
                Time end_time = model.getTimetrip().getEnd_time();
                BusTypeDto typeBus = new BusTypeDto(model.getType().getId(), model.getType().getName(),model.getType().getMaxslot(),model.getType().getNumbers_floor(), model.getType().getNumber_plate(), model.getType().getConvenients());
                TimeDto timeTrip = new TimeDto(model.getTimetrip().getId(), start_time,end_time,start_day, end_day);
                TripResponse response = new TripResponse(model.getId(),name, timeTrip, typeBus, finished);
                tripResponses.add(response);
            }
            return new ResponseEntity<>(tripResponses, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<StatusResponse> deletetTrip(@RequestBody TripRequest request){
        StatusResponse response = new StatusResponse();
        try{
            TripModel model = repo.findTripModelById(request.getId());
            slot_repo.deleteSeatByTripid(request.getId());
            repo.delete(model);
        }catch (RuntimeException e){
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
        }
        response.setStatus(true);
        response.setMessage("OK");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> initTrip(@RequestBody TripModel model){
        try{
            UUID id_type = model.getType().getId();
            BusTypeModel type_bus = type_repo.findBusTypeModelById(id_type);
            int max = type_bus.getMaxslot();
            int number_floor = type_bus.getNumbers_floor();
            System.out.println(max);
            repo.save(model);
            if(number_floor == 2){
                for(int seat = 0; seat< (max /3); seat++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("A" + seat);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
                for(int seat1 = 0; seat1<(max /3) ; seat1++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("B" + seat1);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
                for(int seat1 = 0; seat1<(max /3) ; seat1++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("C" + seat1);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
            }
            else if(number_floor == 1){
                for(int seat = 0; seat< (max /2); seat++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("A" + seat);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
                for(int seat1 = 0; seat1<(max /2) ; seat1++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("B" + seat1);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
            }
            else if(number_floor == 0){
                for(int seat = 0; seat < max; seat++){
                    BusSlotModel slot = new BusSlotModel();
                    slot.set_available(true);
                    slot.setName_slot("A" + seat);
                    slot.setTrip_id(model.getId());
                    slot_repo.save(slot);
                }
            }

        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    private static int convertToSeconds(LocalTime localTime) {
        int seconds = localTime.getHour() * 3600 + localTime.getMinute() * 60 + localTime.getSecond();
        return seconds;
    }
}
