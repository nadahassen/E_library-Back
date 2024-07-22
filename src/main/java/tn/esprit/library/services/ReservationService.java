package tn.esprit.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.library.entities.Reservation;
import tn.esprit.library.repository.IReservationRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationService implements IReservationService{
    @Autowired
    IReservationRepository reservationRepository;
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

   @Override
    public List<Reservation> getReservationsByUser(Long id_user) {

        //return reservationRepository.findByUser(id_user);

       return List.of();
   }

    @Override
    public List<Reservation> getReservationsByBook(Long id_book) {
        //return reservationRepository.findByBook(id_book);
        return List.of();
    }

    @Override
    public Reservation getReservationById(Long id_reservation) {
        Optional<Reservation> reservation = reservationRepository.findById(id_reservation);
        return reservation.orElse(null);    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id_reservation) {
        reservationRepository.deleteById(id_reservation);

    }
}
