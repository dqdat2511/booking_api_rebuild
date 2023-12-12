package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusTicketModel;
import meu.booking_rebuild.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface TicketRepo extends JpaRepository<BusTicketModel, UUID> {
    BusTicketModel findBusTicketModelById(UUID id);

    List<BusTicketModel> findBuTicketModelByTrip(TripModel trip);
}
