package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusTicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TicketRepo extends JpaRepository<BusTicketModel, UUID> {

}
