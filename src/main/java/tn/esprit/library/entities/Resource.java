package tn.esprit.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_resource;

    private String title;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "upload_id")
    @JsonManagedReference(value = "upload-reference")
    private User upload;

    @ManyToOne
    @JoinColumn(name = "approve_id")
    @JsonManagedReference(value = "approve-reference") // Forward reference for approve
    private User approve;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonManagedReference // Manage the forward reference
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "resource_images",
            joinColumns = @JoinColumn(name = "id_resource"),
            inverseJoinColumns = @JoinColumn(name = "id_image")
    )
    private Set<ImageModel> resourceImages;
}
