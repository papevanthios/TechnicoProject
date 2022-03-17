package gr.codehub.accenture.technicoproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VATNumber;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;

    @OneToOne
    private Property property;
}
