package ma.emsi.reservationservice.service;

import ma.emsi.reservationservice.bean.Reservation;
import ma.emsi.reservationservice.clients.ClientRestClient;
import ma.emsi.reservationservice.clients.SalleRestClient;
import ma.emsi.reservationservice.dao.ReservationDao;
import ma.emsi.reservationservice.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationDao reservationDao;
    private ClientRestClient clientRestClient;
    private SalleRestClient salleRestClient;

    public ReservationService(ReservationDao reservationDao,
                              ClientRestClient clientRestClient,
                              SalleRestClient salleRestClient) {
        this.reservationDao = reservationDao;
        this.clientRestClient = clientRestClient;
        this.salleRestClient = salleRestClient;
    }

    public List<Reservation> findAll() {
        List<Reservation> reservations = reservationDao.findAll();
        for (Reservation reservation:
             reservations) {
            reservation.setClient(clientRestClient.findClientById(reservation.getClient_id()));
            reservation.setSalle(salleRestClient.findSalleById(reservation.getSalle_id()));
        }
        return reservationDao.findAll();
    }

    public Reservation save(Reservation newReservation) throws Exception {
        List<Reservation> existingReservations = findAll();
        for(Reservation reservation : existingReservations) {
            if(newReservation.getSalle_id() == reservation.getSalle_id()) {
                if(newReservation.getDateReservation().isEqual(reservation.getDateReservation())) {
                    if(!( (newReservation.getStartHour().isBefore(reservation.getStartHour()) && newReservation.getEndHour().isBefore(reservation.getStartHour()))
                    || (newReservation.getStartHour().isAfter(reservation.getEndHour()) && newReservation.getEndHour().isAfter(reservation.getEndHour())) )) {
                        throw new Exception("Reservation invalid, time conflict");
                    }
                }
            }
        }
        return reservationDao.save(newReservation);
    }

    public Reservation findById(Long id) throws Exception {
        Optional<Reservation> result = reservationDao.findById(id);
        if(result.isPresent()) {
            Reservation reservation = result.get();
            reservation.setClient(clientRestClient.findClientById(reservation.getClient_id()));
            reservation.setSalle(salleRestClient.findSalleById(reservation.getSalle_id()));
            return reservation;
        }
        throw new Exception("Reservation does not exist");
    }

    public void deleteById(Long aLong) {
        reservationDao.deleteById(aLong);
    }

}
