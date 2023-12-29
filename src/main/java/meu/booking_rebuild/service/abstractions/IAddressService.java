package meu.booking_rebuild.service.abstractions;

import meu.booking_rebuild.model.AddressModel;
import meu.booking_rebuild.request.AddressPassangerRequest;
import meu.booking_rebuild.request.PassangerTripRequest;

public interface IAddressService {
    AddressModel createAddress(AddressPassangerRequest request);
}
