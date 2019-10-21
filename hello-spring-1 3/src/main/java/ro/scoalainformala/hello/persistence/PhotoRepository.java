package ro.scoalainformala.hello.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.scoalainformala.hello.model.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    public List<Photo> findByTripId(Long id);
}
