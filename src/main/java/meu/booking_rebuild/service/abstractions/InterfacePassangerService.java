package meu.booking_rebuild.service.abstractions;

import meu.booking_rebuild.request.PassangerTripRequest;

import java.util.UUID;

public interface InterfacePassangerService {
    PassangerTripRequest findPassangerById(UUID id);
    PassangerTripRequest updatePassangerById(PassangerTripRequest request, UUID id);
}
