package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
}
