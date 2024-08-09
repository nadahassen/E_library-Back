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

    private Type type;

    private String image;
    private Status state;
    @OneToMany(mappedBy="user")
    private List<Notification> notificationlist;

    @OneToMany(mappedBy="user")
    private List<Reservation> reservationlist;

    @OneToMany(mappedBy="upload")
    private List<Resource> resources_uploaded;

    @OneToMany(mappedBy="approve")
    private List<Resource> resources_approved;

}
