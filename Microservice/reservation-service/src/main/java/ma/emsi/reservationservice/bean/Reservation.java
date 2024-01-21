package ma.emsi.reservationservice.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.reservationservice.model.Client;
import ma.emsi.reservationservice.model.Salle;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateReservation;

    private LocalTime startHour;

    private LocalTime endHour;

    private Long client_id;
    @Transient
    private Client client;
    private Long salle_id;
    @Transient
    private Salle salle;

}
