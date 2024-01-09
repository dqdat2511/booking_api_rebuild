package meu.booking_rebuild.controller;

import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.model.BusTicketModel;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.model.TripModel;
import meu.booking_rebuild.repository.BusSlotRepo;
import meu.booking_rebuild.repository.TicketRepo;
import meu.booking_rebuild.repository.TripRepo;
import meu.booking_rebuild.response.StatusResponse;
import meu.booking_rebuild.response.TicketResponse;
import meu.booking_rebuild.service.CodeService;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(path = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {
    @Autowired
    private TicketRepo repo;
    @Autowired
    private BusSlotRepo slotRepo;
    @Autowired
    private TripRepo tripRepo;
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addTicket(@RequestBody BusTicketModel model){
        UUID id_trip = model.getTrip().getId();
        try{
            TripModel tripModel = new TripModel();
            tripModel = tripRepo.findTripModelById(id_trip);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        try{
//            TripModel tripModel = tripRepo.findTripModelById(model.getTrip().getId());
            System.out.println(id_trip);
            String code = CodeService.convertDateTimeToCode(new Date());
            model.setCode_ticket(code);
            for(BusSlotModel busSlotModel: model.getSloots()){
                UUID slootId = busSlotModel.getId();
                BusSlotModel slot = slotRepo.findBusSlotModelByIdAndTripId(slootId, model.getTrip().getId());
                if(slot == null){
                    return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if(slot.is_available()) {
                    slot.set_available(false);
                }
                else {
                    return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                slotRepo.save(slot);
            }
            repo.save(model);
            model.getId();
//            BusSlotModel slot = slotRepo.findBusSlotModelByTrip(model.getTrip());
//            if(slot != null)
//                repo.save(model);
//            assert slot != null;
//            slot.set_available(false);
//            slotRepo.save(slot);
            return new ResponseEntity<>(model.getId(), HttpStatus.CREATED); // Return the ID of the saved instance
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

      //  return new ResponseEntity<>("OK", HttpStatus.CREATED);


    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<TicketResponse> getTickets(@RequestParam UUID id){
        BusTicketModel model = repo.findBusTicketModelById(id);
        String name_Trip = model.getTrip().getName();
        String name_customer = model.getCustomer_name();
        String phone = model.getCustomer_phone();
        String code = model.getCode_ticket();
        ArrayList<String> seat = new ArrayList<>();
        for(BusSlotModel slot: model.getSloots()){
            seat.add(slot.getName_slot());
        }
        int number_tickets = model.getNum_tickets();
        TicketResponse response = new TicketResponse(name_Trip,name_customer,phone,code,number_tickets, model.getAddress(), seat);
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateTicket(@RequestBody BusTicketModel model){

        try{
            UUID id = model.getId();
            BusTicketModel temp = repo.findBusTicketModelById(id);
            temp.setAddress(model.getAddress());
            temp.setCustomer_name(model.getCustomer_name());
            temp.setCustomer_phone(model.getCustomer_phone());
            repo.save(temp);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);

    }
}
