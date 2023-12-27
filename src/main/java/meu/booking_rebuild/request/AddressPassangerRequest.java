package meu.booking_rebuild.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressPassangerRequest {
    @NotNull
    private String name;
    private String longitude;
    private String latitude;
    private UUID passanger_id;
}
