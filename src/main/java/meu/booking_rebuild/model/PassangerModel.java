package meu.booking_rebuild.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "passangers")
public class PassangerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @Column(nullable = false, length = 100)
    @Size(max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[ a-zA-Z0-9_.'\\-]+?", message = "Invalid characters in name")
    private String name;

    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại không hợp lệ")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, unique = true)
    private String phone;
    @ColumnDefault("0")
    private int number_trip;
}
