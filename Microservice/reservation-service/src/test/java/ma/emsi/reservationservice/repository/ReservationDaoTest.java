package ma.emsi.reservationservice.repository;

import ma.emsi.reservationservice.bean.Reservation;
import ma.emsi.reservationservice.dao.ReservationDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    @Test
    public void testSaveAndFindAll() {
        // Given
        Reservation reservation = Reservation.builder()
                .dateReservation(LocalDate.now())
                .startHour(LocalTime.of(10, 0))
                .endHour(LocalTime.of(12, 0))
                .client_id(1L)
                .salle_id(1L)
                .build();

        // When
        Reservation savedReservation = reservationDao.save(reservation);
        List<Reservation> reservations = reservationDao.findAll();

        // Then
        Assertions.assertNotNull(savedReservation.getId());
        Assertions.assertFalse(reservations.isEmpty());
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals(reservation.getDateReservation(), reservations.get(0).getDateReservation());
    }

    @Test
    public void testFindById() {
        // Given
        Reservation reservation = Reservation.builder()
                .dateReservation(LocalDate.now())
                .startHour(LocalTime.of(14, 0))
                .endHour(LocalTime.of(16, 0))
                .client_id(2L)
                .salle_id(2L)
                .build();

        Reservation savedReservation = reservationDao.save(reservation);

        // When
        Reservation foundReservation = reservationDao.findById(savedReservation.getId()).orElse(null);

        // Then
        Assertions.assertNotNull(foundReservation);
        Assertions.assertEquals(savedReservation.getId(), foundReservation.getId());
        Assertions.assertEquals(savedReservation.getDateReservation(), foundReservation.getDateReservation());
    }

    @Test
    public void testDeleteById() {
        // Given
        Reservation reservation = Reservation.builder()
                .dateReservation(LocalDate.now())
                .startHour(LocalTime.of(18, 0))
                .endHour(LocalTime.of(20, 0))
                .client_id(3L)
                .salle_id(3L)
                .build();

        Reservation savedReservation = reservationDao.save(reservation);

        // When
        reservationDao.deleteById(savedReservation.getId());

        // Then
        Reservation deletedReservation = reservationDao.findById(savedReservation.getId()).orElse(null);
        Assertions.assertNull(deletedReservation);
    }
}
