package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusTypeResponse {
    private UUID id;
    private String name;
    private int maxslot;
    private int numbers_floor;
    private String convenients;
    private String number_plate;
}
