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
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_resourcce;

    private String title;

    private Specialty specialty;

    private Status status;

    @ManyToOne
    private User upload;

    @ManyToOne
    private User approve;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "resource")
    private List<Document> documents;

}
