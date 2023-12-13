package meu.booking_rebuild.repository;

import meu.booking_rebuild.model.TimeTripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
@Repository
public interface TimeTripRepo extends JpaRepository<TimeTripModel, Integer> {
    @Query(value = "SELECT * FROM time_trip WHERE time_trip.start_day >= :startDay AND time_trip.end_day >= :endDay", nativeQuery = true)
    List<TimeTripModel> findByStartDayGreaterThanEqualAndEndDayGreaterThanEqual(@Param("startDay") LocalDate startDay, @Param("endDay") LocalDate endDay);

}
