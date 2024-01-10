package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "tickets")
public class BusTicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String customer_name;
    private String customer_phone;
    private String address;
    private String code_ticket;

    @OneToOne
    @JoinColumn(name = "id_seat")
    private BusSlotModel id_seat;
    @ManyToOne
    @JoinColumn(name = "id_trip")
    private TripModel trip;


}
