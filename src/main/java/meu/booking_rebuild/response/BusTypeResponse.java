package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusTypeResponse {
    private String name;
    private int maxslot;
    private int numbers_floor;
    private String convenients;
}
