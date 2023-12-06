package meu.booking_rebuild.controller;

import meu.booking_rebuild.dto.BusTypeDto;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.repository.BusTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/v1/admin/bus/type")
public class TypeBusController {
    @Autowired
    private BusTypeRepo repo;
    @PostMapping
    public ResponseEntity<?> addType(@RequestBody BusTypeModel model){
        try{
            repo.save(model);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?>updateType(@RequestParam UUID id,
                                       @RequestBody BusTypeModel model){
        try{
            BusTypeModel temp = repo.findBusTypeModelById(id);
            temp.setConvenients(model.getConvenients());
            temp.setName(model.getName());
            temp.setMaxslot(model.getMaxslot());
            temp.setNumbers_floor(model.getNumbers_floor());
            repo.save(temp);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
