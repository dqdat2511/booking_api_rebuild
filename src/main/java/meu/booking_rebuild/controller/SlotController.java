package meu.booking_rebuild.controller;

import jakarta.servlet.http.HttpServletResponse;
import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.model.BusTicketModel;
import meu.booking_rebuild.model.TripModel;
import meu.booking_rebuild.repository.BusSlotRepo;
import meu.booking_rebuild.repository.TicketRepo;
import meu.booking_rebuild.repository.TripRepo;
import meu.booking_rebuild.response.CheckValidTripResponse;
import meu.booking_rebuild.response.TicketResponse;
import meu.booking_rebuild.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/seat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SlotController {
    @Autowired
    private BusSlotRepo repo;
    @Autowired
    private TripRepo tripRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private ReportService service;
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getSeat(){
//        List<BusSlotModel>  = repo.findAll();
        List<BusSlotModel> list = repo.findAllByOrderByTrip_idAscNameAsc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/trip")
    @ResponseBody
    public ResponseEntity<?> getSeatForTrip(@RequestParam UUID id){
        List<BusSlotModel> models = repo.findBusSlotModelByTripId(id);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
    @GetMapping("check")
    @ResponseBody
    public CheckValidTripResponse checkPassanger(@RequestParam UUID id){
        int count =  repo.getNumbersTripHavePassanger(id);
        CheckValidTripResponse response = new CheckValidTripResponse();
        if(count == 0){
            response.setMessage("Have no passange");
            response.setStatus(false);
            return response;
        }
        response.setMessage("Have passanger");
        response.setStatus(true);
        return response;
    }
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> GetList(HttpServletResponse Httpresponse, @RequestParam UUID id) throws Exception {
        TripModel tripModel = tripRepo.findTripModelById(id);
        List<BusTicketModel> ticketModels = ticketRepo.findBuTicketModelByTrip(tripModel);
        ArrayList<TicketResponse> ticketResponses = new ArrayList<>();
        for(BusTicketModel ticketModel: ticketModels){
            String customer_name = ticketModel.getCustomer_name();
            String code = ticketModel.getCode_ticket();
            String number_phone = ticketModel.getCustomer_phone();
            int number_tickets = ticketModel.getNum_tickets();
            ArrayList<String> seat = new ArrayList<>();
            for(BusSlotModel slot: ticketModel.getSloots()){
                seat.add(slot.getName_slot());
            }
            String name_trip = ticketModel.getTrip().getName();
            TicketResponse response = new TicketResponse(name_trip,customer_name,number_phone,code,number_tickets,ticketModel.getAddress(),seat);
            ticketResponses.add(response);
        }
//        Httpresponse.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment;filename=danh_sach_hanh_khach.xls";
//        Httpresponse.setHeader(headerKey, headerValue);
//        service.generateExcel(Httpresponse,ticketResponses);
//        Httpresponse.flushBuffer();
//        System.out.println(tripModel);
        return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
//
//        TripModel tripModel = tripRepo.findTripModelById(model.getTrip().getId());
//        System.out.println(tripModel);
//        System.out.println(ticketModel);

    }
}
