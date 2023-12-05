package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.BusTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusTypeRepo extends JpaRepository<BusTypeModel, UUID> {
}
