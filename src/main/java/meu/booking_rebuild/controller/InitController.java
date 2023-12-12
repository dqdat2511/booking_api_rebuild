package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.*;
import meu.booking_rebuild.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("api/v1/admin/bus/trip")
public class InitController {
    @Autowired
    private TripRepo repo;
    @Autowired
    private BusTypeRepo type_repo;
    @Autowired
    private BusSlotRepo slot_repo;
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
            else{
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
}
