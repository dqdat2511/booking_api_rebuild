package meu.booking_rebuild.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
    private Time start_time;
    private Time end_time;
    private Date start_day;
    private Date end_day;
}
