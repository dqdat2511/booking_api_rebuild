package meu.booking_rebuild.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "customer_tickets")
public class TicketCustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
