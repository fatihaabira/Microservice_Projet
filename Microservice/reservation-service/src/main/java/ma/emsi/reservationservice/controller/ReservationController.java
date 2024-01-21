package ma.emsi.reservationservice.controller;

import ma.emsi.reservationservice.bean.Reservation;
import ma.emsi.reservationservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Reservation entity) {
        try {
            Reservation savedReservation = reservationService.save(entity);
            return new ResponseEntity<>(savedReservation, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to save reservation: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Reservation foundReservation = reservationService.findById(id);
            return new ResponseEntity<>(foundReservation, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to get reservation: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
