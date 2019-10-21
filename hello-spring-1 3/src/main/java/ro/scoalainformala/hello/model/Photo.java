package ro.scoalainformala.hello.model;


import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;


@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    @Lob
    private byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private Trip trip;

    public Photo(){};

    public Photo(String title, String description, byte[] photo) {
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    public Photo(String title, String description, byte[] photo, Trip trip) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.trip = trip;
    }

    public Photo(long id, String title, String description, byte[] photo, Trip trip) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.trip = trip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo1 = (Photo) o;
        return id == photo1.id &&
                Objects.equals(title, photo1.title) &&
                Objects.equals(description, photo1.description) &&
                Arrays.equals(photo, photo1.photo) &&
                Objects.equals(trip, photo1.trip);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, description, trip);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
