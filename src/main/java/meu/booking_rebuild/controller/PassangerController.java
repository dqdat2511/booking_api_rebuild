package meu.booking_rebuild.controller;

import jakarta.validation.Valid;
import meu.booking_rebuild.config.Constants;
import meu.booking_rebuild.model.PassangerModel;
import meu.booking_rebuild.repository.PassangersRepository;
import meu.booking_rebuild.request.PassangerTripRequest;
import meu.booking_rebuild.response.GenericResponse;
import meu.booking_rebuild.response.PassangerTripResponse;
import meu.booking_rebuild.service.abstractions.InterfacePassangerService;
import meu.booking_rebuild.service.concretions.PassangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/passanger", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassangerController {
    @Autowired
    private  PassangersRepository passangersRepository;
    private InterfacePassangerService passangerService;
    PassangerTripResponse response = new PassangerTripResponse();
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PassangerTripResponse> addPassanger(@RequestBody @Valid PassangerModel model){
        try{
            if(passangersRepository.findPassangerModelByPhone(model.getPhone())){

                response.fail(Constants.MESSAGE_DUPLICATED_FAIL);
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(403));
            }
            passangersRepository.save(model);
            response.success(Constants.MESSAGE_ADDED_SUCCESSFULLY, model.getId());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
        }catch (RuntimeException e){
            PassangerTripResponse response = new PassangerTripResponse();
            response.fail(e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(403));
        }
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPassanger(@RequestParam UUID id){
        try{
            Optional<PassangerTripRequest> model = passangersRepository.findPassengerById(id);
            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(
                    403
            ));
        }
    }
    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPassangerByPhone(@RequestParam String phone){
        try{
            List<PassangerTripRequest> model = passangersRepository.findPassangersByPhone(phone);
            return new ResponseEntity<>(model, HttpStatusCode.valueOf(200));
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(
                    403
            ));
        }
    }

    /*getAllPhone*/
    @GetMapping("/phoneNumbers")
    public ResponseEntity<?> getAllPhoneNumbers() {
        List<String> allPhone = passangersRepository.findAllPhoneNumbers();
        return new ResponseEntity<>(allPhone, HttpStatus.OK);
    }
}
