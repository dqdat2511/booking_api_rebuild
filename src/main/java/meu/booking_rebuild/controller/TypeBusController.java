package meu.booking_rebuild.controller;

import meu.booking_rebuild.dto.BusTypeDto;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.repository.BusTypeRepo;
import meu.booking_rebuild.response.BusTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
@Controller
@RequestMapping(path = "/bus/type", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeBusController {
    @Autowired
    private BusTypeRepo repo;
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addType(@RequestBody BusTypeModel model){
        try{
            repo.save(model);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PutMapping
    @ResponseBody
    public ResponseEntity<?>updateType(@RequestBody BusTypeDto model){
        try{
            UUID id = model.getId();
            BusTypeModel temp = repo.findBusTypeModelById(id);
            temp.setConvenients(model.getConvenients());
            temp.setName(model.getName());
            temp.setMaxslot(model.getMaxslot());
            temp.setNumbers_floor(model.getNumbers_floor());
            temp.setNumber_plate(model.getNumber_plate());
            repo.save(temp);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllBus(){
        try{
            List<BusTypeModel> listBus = repo.findAll();
            ArrayList<BusTypeResponse> reponseList = new ArrayList<>();
            for(BusTypeModel bus: listBus){
                UUID id = bus.getId();
                String name = bus.getName();
                int maxslot = bus.getMaxslot();
                int numbers_floor = bus.getNumbers_floor();
                String convenients = bus.getConvenients();
                BusTypeResponse busResponse = new BusTypeResponse(id, name, maxslot, numbers_floor, convenients, bus.getNumber_plate());
                reponseList.add(busResponse);
            }
            return new ResponseEntity<>(reponseList, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return   new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
