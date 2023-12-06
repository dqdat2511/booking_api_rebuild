package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.repository.BusSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("api/v1/seat")
public class SlotController {
    @Autowired
    private BusSlotRepo repo;
    @GetMapping
    public ResponseEntity<?> getSeat(){
        List<BusSlotModel> list = repo.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
