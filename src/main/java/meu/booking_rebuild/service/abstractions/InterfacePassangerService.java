package meu.booking_rebuild.service.abstractions;

import meu.booking_rebuild.request.PassangerTripRequest;

import java.util.List;
import java.util.UUID;

public interface InterfacePassangerService {
    PassangerTripRequest findPassangerById(UUID id);
    List<PassangerTripRequest>  findPassangerByPhone(String phone);
    boolean check(String phone);
    PassangerTripRequest updatePassangerById(PassangerTripRequest request, UUID id);
}
