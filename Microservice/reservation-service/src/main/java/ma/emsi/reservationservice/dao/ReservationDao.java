package ma.emsi.reservationservice.dao;

import ma.emsi.reservationservice.bean.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long> {
}
