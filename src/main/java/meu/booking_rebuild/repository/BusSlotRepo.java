package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusSlotModel;
import meu.booking_rebuild.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface BusSlotRepo extends JpaRepository<BusSlotModel, UUID> {
    @Query("SELECT b FROM BusSlotModel b WHERE b.id = :id AND b.trip_id = :tripId")
    BusSlotModel findBusSlotModelByIdAndTripId(@Param("id") UUID id, @Param("tripId") UUID tripId);
    @Query("SELECT bs FROM BusSlotModel bs ORDER BY bs.trip_id, SUBSTRING(bs.name_slot, 1, 1), CAST(SUBSTRING(bs.name_slot, 2) AS INTEGER)\n")
    List<BusSlotModel> findAllByOrderByTrip_idAscNameAsc();
    @Query("SELECT bs FROM BusSlotModel bs WHERE bs.trip_id = :tripId ORDER BY SUBSTRING(bs.name_slot, 1, 1), CAST(SUBSTRING(bs.name_slot, 2) AS INTEGER)\n")
    List<BusSlotModel> findBusSlotModelByTripId(@Param("tripId") UUID tripId);
}
