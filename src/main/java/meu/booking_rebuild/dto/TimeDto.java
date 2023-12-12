package meu.booking_rebuild.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class TimeDto {
    private Time start_time;
    private Time end_time;
    private Date start_day;
    private Date end_day;
}
