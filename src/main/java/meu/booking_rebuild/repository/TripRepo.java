package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface TripRepo extends JpaRepository<TripModel, UUID> {
    TripModel findTripModelById(UUID id);
    @Query("select t from TripModel t join t.timetrip tt order by tt.start_day asc, tt.start_time asc")
    List<TripModel> findAllByOrderByTimeTripStartDayAscTimeTripStartTimeAsc();
}
