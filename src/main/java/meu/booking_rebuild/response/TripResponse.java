package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meu.booking_rebuild.dto.BusTypeDto;
import meu.booking_rebuild.dto.TimeDto;
import meu.booking_rebuild.model.BusTypeModel;
import meu.booking_rebuild.model.TimeTripModel;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TripResponse {
    private UUID id;
    private String name;
    private TimeDto time;
    private BusTypeDto seats;
    private boolean isFinished;
}
