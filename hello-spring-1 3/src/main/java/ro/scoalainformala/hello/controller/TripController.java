package ro.scoalainformala.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.scoalainformala.hello.service.TripService;
import ro.scoalainformala.hello.service.dto.TripsDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class TripController {

    @Autowired
    TripService tripService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String TripsDTO(Model model, Principal principal) {
        List<TripsDTO> tripsDTOList = tripService.getTrips(principal.getName());
        model.addAttribute("tripsDTOList", tripsDTOList);
//        principal.getName();
        return "trips";
    }

    @GetMapping("/addTrip")
    public String getTrip (Model model) {
        model.addAttribute("tripDTO", new TripsDTO());
        return "addTrip";
    }

    @PostMapping("/addTrip")
    public String addTrip (@RequestParam("photo1") MultipartFile file1,
                           @RequestParam("photo2") MultipartFile file2,
                           @ModelAttribute TripsDTO tripsDTO,Principal principal) {
        tripService.addTripByUserName(tripsDTO, principal.getName(), file1, file2);
        return "redirect:/";
    }

    @GetMapping("/deleteTrip/{tripId}")
    public String deleteTripById(@PathVariable Long tripId){
        tripService.deleteTrip(tripId);
        return "redirect:/";
    }
    @GetMapping("/editTrip/{tripId}")
    public String editTripById(@PathVariable Long tripId, Model model){
        TripsDTO tripsDTO = tripService.getTrip(tripId);
        model.addAttribute("tripDTO", tripsDTO);
        return "editTrip";
    }

    @PostMapping("/editTrip")
    public String editTripById (@RequestParam("photo1") MultipartFile file1,
                                @RequestParam("photo2") MultipartFile file2,
                                @ModelAttribute TripsDTO tripsDTO) {
        tripService.editTrip(tripsDTO, file1, file2);
        return "redirect:/";
    }

    @GetMapping("/img/{id}/{photoNumber}")
    public void getImage(@PathVariable("id") Long id, @PathVariable("photoNumber") int photoNumber, HttpServletResponse response) throws IOException {
        TripsDTO tripsDTO = tripService.getTripDetails(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        byte[] imageData = tripService.getTripPhotos(id).get(photoNumber).getPhoto();
        try (OutputStream out = response.getOutputStream()) {
            out.write(imageData);
        }
    }

    @GetMapping("/details/{tripId}")
    public String goToTripDetails(@PathVariable Long tripId, Model model){
        TripsDTO tripsDTO = tripService.getTripDetails(tripId);
        model.addAttribute("tripDTO", tripsDTO);
        return "details";
    }
}