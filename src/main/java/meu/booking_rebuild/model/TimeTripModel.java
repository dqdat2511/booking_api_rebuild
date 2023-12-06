package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.sql.Time;
import java.sql.Date;

@Entity
@Data
@Table(name = "time_trip")
public class TimeTripModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Time start_time;
    private Time end_time;
    private Date start_day;
    private Date end_day;
//    @OneToOne
//    @JoinColumn(name = "trip_id", unique = true)
//    private TripModel trip;
    @OneToMany(mappedBy = "timetrip") // Đổi từ @OneToOne thành @OneToMany
    private List<TripModel> trips;


}
