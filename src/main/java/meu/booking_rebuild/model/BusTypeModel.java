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
    @Lob
    @Column(columnDefinition = "TEXT")
    private String convenients;

}
