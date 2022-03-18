package gr.codehub.accenture.technicoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class PropertyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(unique=true)// To ensure a field value is unique you can write
//    @Size(min=9, max=9)// specific format for VAT with 9 digits
    private int vatNumber;

    private String firstName;

    private String lastName;

    private String address;

//    @Size(min=10, max=10) // specific format for phoneNumber with 10 digits
    private String phoneNumber;

    private String email;// we need a format constraint here @ and dot

    private String username;

    private String password;

//    @OneToOne
//    private Property property;
}