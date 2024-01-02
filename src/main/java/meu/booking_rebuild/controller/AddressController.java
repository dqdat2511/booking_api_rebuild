package meu.booking_rebuild.controller;

import meu.booking_rebuild.config.Constants;
import meu.booking_rebuild.model.AddressModel;
import meu.booking_rebuild.repository.AddressRepository;
import meu.booking_rebuild.request.AddressPassangerRequest;
import meu.booking_rebuild.request.GetAddressRequest;
import meu.booking_rebuild.response.GenericResponse;
import meu.booking_rebuild.response.GetAddressResponse;
import meu.booking_rebuild.service.abstractions.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse addAddress(@RequestBody AddressPassangerRequest request){
        try{
            AddressModel model = addressService.createAddress(request);
            GenericResponse response = new GenericResponse(Constants.MESSAGE_ADDED_SUCCESSFULLY);
            return response;
        }catch (RuntimeException e){
            throw new HttpMessageConversionException(e.getMessage());
        }
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetAddressResponse getAddressByPhone(@RequestParam String phone){
        try{
            GetAddressResponse response = addressService.getAddressByPhone(phone);
            return response;
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
