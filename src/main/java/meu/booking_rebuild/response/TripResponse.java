package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TripResponse {
    private String name;
    private Date start_day;
    private Date end_day;
    private Time start_time;
    private Time end_time;
    private int number_seats;
    private boolean isFinished;
}
