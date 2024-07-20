package tn.esprit.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_book;

    private String name;

    private String author;

    private String sujet;

    private String year;

    private Specialty specialty;

    private boolean available;

    @OneToMany(mappedBy="book")
    private List<Reservation> reservations;
}
