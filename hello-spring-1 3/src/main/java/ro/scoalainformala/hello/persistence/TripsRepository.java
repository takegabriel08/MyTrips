package ro.scoalainformala.hello.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.scoalainformala.hello.model.Trip;

public interface TripsRepository extends JpaRepository<Trip, Long> {
    public void removeTripById(long id);

}
