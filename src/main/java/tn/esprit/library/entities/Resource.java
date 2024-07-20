package tn.esprit.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
