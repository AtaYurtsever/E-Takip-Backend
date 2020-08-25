package takip.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import takip.back.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<Event> findById(Long id);
    List<Event> findByTitle(String title);


    @Query("select e from Event e where start_date >= now()")
    List<Event> getAllAfterToday();

    @Query("select e from Event e where event_organizer=?1 ")
    List<Event> getWithId(Long id);

}
