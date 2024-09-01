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
    private String phone;
    private String firstname;

    private String lastname;

    private int priority;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String image;
    @Enumerated(EnumType.STRING)
    private Status state;
    private String sexe;
    @OneToMany(mappedBy="user")
    private List<Notification> notificationlist;

    @OneToMany(mappedBy="user")
    private List<Reservation> reservationlist;

    @OneToMany(mappedBy="upload")
    private List<Resource> resources_uploaded;

    @OneToMany(mappedBy="approve")
    private List<Resource> resources_approved;

}
