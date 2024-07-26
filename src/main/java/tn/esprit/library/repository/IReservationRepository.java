package tn.esprit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.library.entities.Book;
import tn.esprit.library.entities.Reservation;
import tn.esprit.library.entities.User;

import java.util.List;

@Repository
public interface IReservationRepository  extends JpaRepository<Reservation,Long>
{
    List<Reservation> findByUser(User user);
    List<Reservation> findByBook(Book book);
}
