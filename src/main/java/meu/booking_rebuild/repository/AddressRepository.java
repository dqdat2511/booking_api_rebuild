package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.AddressModel;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
    @Query("SELECT t FROM AddressModel t WHERE t.passanger.id = :user_id")
    AddressModel getAddressModelByUserId(@Param("user_id") UUID user_id);
}
