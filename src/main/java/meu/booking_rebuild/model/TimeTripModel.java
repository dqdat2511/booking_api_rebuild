package meu.booking_rebuild.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "timetrip") // Đổi từ @OneToOne thành @OneToMany

    private List<TripModel> trips;


}
