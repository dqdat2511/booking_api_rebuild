package meu.booking_rebuild.repository;

import jakarta.transaction.Transactional;
import meu.booking_rebuild.model.BusSlotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query("SELECT COUNT(*) FROM BusSlotModel bs where bs.trip_id = :tripId and bs.is_available = false")
    int getNumbersTripHavePassanger(@Param("tripId") UUID tripId);
    @Modifying
    @Transactional
    @Query("DELETE FROM BusSlotModel bs where bs.trip_id = :tripId ")
    void deleteSeatByTripid(@Param("tripId") UUID tripId);
}
