package meu.booking_rebuild.service.abstractions;

import meu.booking_rebuild.model.AddressModel;
import meu.booking_rebuild.request.AddressPassangerRequest;
import meu.booking_rebuild.request.GetAddressRequest;
import meu.booking_rebuild.request.PassangerTripRequest;
import meu.booking_rebuild.response.GetAddressResponse;
import org.springframework.data.domain.Slice;

public interface IAddressService {
    AddressModel createAddress(AddressPassangerRequest request);
    GetAddressResponse getAddressByPhone(String phone);
}
