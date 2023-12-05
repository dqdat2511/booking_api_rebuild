package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.BusFloorModel;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.repository.BusFloorRepo;
import meu.booking_rebuild.repository.BusTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/admin/bus/type")
public class InitController {
    @Autowired
    private BusTypeRepo repo;
    @PostMapping
    public ResponseEntity<?> initBusType(@RequestBody BusTypeModel model){
        try{
            repo.save(model);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
