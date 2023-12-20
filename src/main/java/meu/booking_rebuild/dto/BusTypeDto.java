package meu.booking_rebuild.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusTypeDto {
    private UUID id;
    private String name;
    private int maxslot;
    private int numbers_floor;
    private String number_plate;
    @Lob
    private String convenients;
}
