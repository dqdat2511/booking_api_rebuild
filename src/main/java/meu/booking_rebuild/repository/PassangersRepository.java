package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.PassangerModel;
import meu.booking_rebuild.request.PassangerTripRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PassangersRepository extends JpaRepository<PassangerModel, UUID> {
    PassangerModel findPassangerModelById(UUID id);
    @Query("SELECT new meu.booking_rebuild.request.PassangerTripRequest(id, name, phone) FROM PassangerModel  WHERE id= :id")
    Optional<PassangerTripRequest> findPassengerById(UUID id);
}
