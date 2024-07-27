package tn.esprit.library.services;

import tn.esprit.library.entities.Reservation;

import java.util.List;

public interface IReservationService {

    public List<Reservation> getAllReservations();

    public List<Reservation> getReservationsByUser(Long id_user);

    public List<Reservation> getReservationsByBook(Long id_book);

    public Reservation getReservationById(Long id_reservation);

    public Reservation addReservation(Reservation reservation);

    public void deleteReservation(Long id_reservation);
}
