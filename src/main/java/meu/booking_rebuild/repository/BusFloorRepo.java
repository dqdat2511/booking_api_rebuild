package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusFloorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusFloorRepo extends JpaRepository<BusFloorModel, UUID> {
}
