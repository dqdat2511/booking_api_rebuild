package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Entity
@Table(name = "trips")
@Data
public class TripModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @OneToOne
    @JoinColumn(name = "timetrip_id", unique = true)
    private TimeTripModel timetrip;
    @OneToOne
    @JoinColumn(name = "bus_type_id", unique = true)
    private BusTypeModel type;
}
