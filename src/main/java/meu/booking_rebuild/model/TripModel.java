package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Data
public class TripModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "timetrip_id")
    private TimeTripModel timetrip;
    @ManyToOne
    @JoinColumn(name = "bus_type_id")
    private BusTypeModel type;
    @OneToMany(mappedBy = "trip") // Đổi từ @OneToOne thành @OneToMany
    private List<BusTicketModel> tickets;
}
