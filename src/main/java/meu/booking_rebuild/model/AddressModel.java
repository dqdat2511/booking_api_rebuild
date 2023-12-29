package meu.booking_rebuild.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Setter
@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(unique = true)
    private UUID id;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String longitude;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String latitude;
    @ManyToOne
    @JoinColumn(name = "passanger_id", unique = true)
    private PassangerModel passanger;

    public AddressModel(String name, String longitude, String latitude, PassangerModel passanger) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.passanger = passanger;
    }
}
