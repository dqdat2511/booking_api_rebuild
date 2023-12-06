package meu.booking_rebuild.dto;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class BusTypeDto {
    private String name;
    private int maxslot;
    private int numbers_floor;
    @Lob
    private String convenients;
}
