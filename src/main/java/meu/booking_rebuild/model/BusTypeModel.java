package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "bus_types")
public class BusTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int maxslot;
    private int numbers_floor;
    @Column(columnDefinition = "TEXT")
    private String convenients;
    @OneToMany(mappedBy = "type") // Đổi từ @OneToOne thành @OneToMany
    private List<TripModel> trips;

}
