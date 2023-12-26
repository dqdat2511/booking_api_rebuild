package meu.booking_rebuild.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;
    @NotNull
    private String name;
    private String longitude;
    private String latitude;
    @ManyToOne
    @JoinColumn(name = "passanger_id", unique = true)
    private PassangerModel passanger;
}
