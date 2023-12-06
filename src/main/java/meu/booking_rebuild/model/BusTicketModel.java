package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tickets")
public class BusTicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String customer_name;
    private String customer_phone;
    private String address;
    private String code_ticket;
    private int num_tickets;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripModel trip;
    @ManyToMany
    @JoinTable(name = "customer_tickes",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "customer_seat_id"))
    private List<BusSlotModel> sloots;

}
