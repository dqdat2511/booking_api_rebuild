package meu.booking_rebuild.request;

import lombok.*;
import meu.booking_rebuild.model.AddressModel;
import meu.booking_rebuild.model.PassangerModel;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PassangerTripRequest {
    private UUID id;
    private String name;
    private String phone;
}
