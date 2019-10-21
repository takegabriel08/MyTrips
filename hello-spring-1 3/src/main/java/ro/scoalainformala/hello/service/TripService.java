package ro.scoalainformala.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.scoalainformala.hello.model.Photo;
import ro.scoalainformala.hello.model.Trip;
import ro.scoalainformala.hello.model.User;
import ro.scoalainformala.hello.persistence.PhotoRepository;
import ro.scoalainformala.hello.persistence.TripsRepository;
import ro.scoalainformala.hello.persistence.UserRepository;
import ro.scoalainformala.hello.service.dto.TripsDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TripService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    TripsRepository tripsRepository;

    @Autowired
    TripService tripService;

    @Autowired
    UserRepository userRepository;

    public List<TripsDTO> getTrips(String userName) {
        List<Trip> tripList = this.userRepository.findByUsername(userName).getTripList();
        return tripList.stream().map(TripService::from).collect(toList());
    }

    public void addTripByUserName(TripsDTO tripsDTO, String username, MultipartFile file1, MultipartFile file2) {
        try {
            if (this.isDateValid(tripsDTO.getStartDate(), tripsDTO.getEndDate())) {
                byte[] photo1AsByteArray = file1.getBytes();
                byte[] photo2AsByteArray = file2.getBytes();
                User user = this.userRepository.findByUsername(username);
                Trip trip = buildTrip(tripsDTO);
                trip.setUser(user);
                Photo photo1 = buildPhoto1(tripsDTO, photo1AsByteArray);
                Photo photo2 = buildPhoto2(tripsDTO, photo2AsByteArray);

                //salveaza un trip in baza de date si il returneaza si totodata il seteaza pe obiectul photo
                Trip savedTrip = tripsRepository.save(trip);
                photo1.setTrip(savedTrip);
                photo2.setTrip(savedTrip);
                photoRepository.save(photo1);
                photoRepository.save(photo2);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad file input!");
        }
    }

    public static Photo buildPhoto1(TripsDTO tripsDTO, byte[] photo) {
        return new Photo(tripsDTO.getPhoto1Title(), tripsDTO.getPhoto1Description(), photo);
    }

    public static Photo buildPhoto2(TripsDTO tripsDTO, byte[] photo) {
        return new Photo(tripsDTO.getPhoto2Title(), tripsDTO.getPhoto2Description(), photo);
    }

    public TripsDTO getTrip(Long id) {
        Optional<Trip> tripOptional = tripsRepository.findById(id);
        if (tripOptional.isPresent()) {
            return from(tripOptional.get());
        } else {
            throw new IllegalArgumentException("No trip found");
        }
    }

    public static Trip buildTrip(final TripsDTO tripsDTO) {
        return new Trip(tripsDTO.getId(), tripsDTO.getTitle(), tripsDTO.getDescription(),
                LocalDate.parse(tripsDTO.getStartDate()), LocalDate.parse(tripsDTO.getEndDate()));
    }

    public void deleteTrip(long tripId) {
        tripsRepository.deleteById(tripId);
    }

    public static TripsDTO from(final Trip trip) {
        List<Photo> photoList = trip.getPhotoList();
        return new TripsDTO(trip.getId(), trip.getTitle(), trip.getDescription(),
                photoList.get(0).getTitle(), photoList.get(1).getTitle(),
                photoList.get(0).getDescription(), photoList.get(1).getDescription(),
                trip.getStartDate().toString(), trip.getEndDate().toString());
    }

    public static TripsDTO buildTripDetails(final Trip trip, Photo photo1, Photo photo2) {
        return new TripsDTO(trip.getId(), trip.getTitle(),
                trip.getDescription(), photo1.getTitle(),
                photo2.getTitle(), photo1.getDescription(),
                photo2.getDescription());
    }


    public void editTrip(TripsDTO tripsDTO, MultipartFile file1, MultipartFile file2) {
        try {
            if (this.isDateValid(tripsDTO.getStartDate(), tripsDTO.getEndDate())) {

                Trip trip = tripsRepository.getOne(tripsDTO.getId());
                trip.setTitle(tripsDTO.getTitle());
                trip.setDescription(tripsDTO.getDescription());
                trip.setStartDate(LocalDate.parse(tripsDTO.getStartDate()));
                trip.setEndDate(LocalDate.parse(tripsDTO.getEndDate()));
                Trip savedTrip = tripsRepository.save(buildTrip(tripsDTO));
                List<Photo> photoList = savedTrip.getPhotoList();

                if(!file1.isEmpty()) {
                    byte[] photo1AsByteArray = file1.getBytes();
                    photoList.get(0).setPhoto(photo1AsByteArray);
                    photoList.get(0).setTitle(tripsDTO.getPhoto1Title());
                    photoList.get(0).setDescription(tripsDTO.getPhoto1Description());
                    this.photoRepository.save(photoList.get(0));
                }

                if(!file2.isEmpty()){
                    byte[] photo2AsByteArray = file2.getBytes();
                    photoList.get(1).setPhoto(photo2AsByteArray);
                    photoList.get(1).setTitle(tripsDTO.getPhoto1Title());
                    photoList.get(1).setDescription(tripsDTO.getPhoto1Description());
                    this.photoRepository.save(photoList.get(1));
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad file input!");
        }
    }

    public TripsDTO getTripDetails(Long tripId) {
        Optional<Trip> tripOptional = tripsRepository.findById(tripId);
        List<Photo> photoList = photoRepository.findByTripId(tripId);
        return buildTripDetails(tripOptional.get(), photoList.get(0), photoList.get(1));
    }

    public List<Photo> getTripPhotos(Long tripId) {
        Optional<Trip> tripOptional = tripsRepository.findById(tripId);
        List<Photo> photoList = photoRepository.findByTripId(tripId);
        return photoList;
    }

    public boolean isDateValid(String stringStartDate, String stringEndDate) {
        if(stringStartDate.isEmpty() || stringEndDate.isEmpty()){
            throw new IllegalArgumentException("Date cannot be empty");
        }
        LocalDate startDate = LocalDate.parse(stringStartDate);
        LocalDate endDate = LocalDate.parse(stringEndDate);
        LocalDate now = LocalDate.now();

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is greater then end date");
        }
        if (startDate.isAfter(now)) {
            throw new IllegalArgumentException("Start date is greater then current date");
        }
        return true;
    }
}