package tn.esprit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.library.entities.Reservation;

@Repository
public interface IReservationRepository  extends JpaRepository<Reservation,Long>
{
}
