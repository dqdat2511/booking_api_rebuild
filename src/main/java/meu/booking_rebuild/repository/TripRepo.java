package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TripRepo extends JpaRepository<TripModel, UUID> {
    TripModel findTripModelById(UUID id);
}
