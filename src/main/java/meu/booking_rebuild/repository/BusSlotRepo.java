package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BusSlotRepo extends JpaRepository<BusSlotModel, UUID> {
    @Query("SELECT b FROM BusSlotModel b WHERE b.id = :id AND b.trip_id = :tripId")
    BusSlotModel findBusSlotModelByIdAndTripId(@Param("id") UUID id, @Param("tripId") UUID tripId);
}
