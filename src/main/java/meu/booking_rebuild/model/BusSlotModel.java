package meu.booking_rebuild.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "bus_slot")
public class BusSlotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name_slot;
    @ColumnDefault("true")
    private boolean is_available;
//    @ManyToOne
//    @JoinColumn(name = "trip_id")
    private UUID trip_id;
//    @JsonIgnore
//    @OneToMany(mappedBy = "sloots")
//    private List<BusTicketModel> tickets;
    @OneToOne
    private BusFloorModel bus_floor;
    @OneToOne
    private BusTypeModel bus_type;

}
