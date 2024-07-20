package tn.esprit.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    private Long id_reservation;

    private Date reservation_date;

    private Date return_date;

    private Status status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

}
