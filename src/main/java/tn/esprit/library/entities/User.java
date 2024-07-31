package tn.esprit.library.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String password;
    private String mail;
    private String firstname;
    private String lastname;
    private int priority;
    private Specialty specialty;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String image;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notificationlist;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservationlist;

    @OneToMany(mappedBy = "upload")
    @JsonBackReference(value = "upload-reference") // Back reference for upload
    private List<Resource> resources_uploaded;

    @OneToMany(mappedBy = "approve")
    @JsonBackReference(value = "approve-reference") // Back reference for upload
    private List<Resource> resources_approved;

    // Additional fields and methods if necessary
}
