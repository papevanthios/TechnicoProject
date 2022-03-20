package gr.codehub.accenture.technicoproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true, length = 9)  // To ensure a field value is unique you can write
    @Size(min=9, max=9)   // specific format for VAT with 9 digits
    private String vatNumber;
    private String firstName;
    private String lastName;
    private String address;
    @Column(unique=true, length = 10)
    @Size(min=10, max=10) // specific format for phoneNumber with 10 digits
    private String phoneNumber;
    @Email                // we need a format constraint here @ and dot
    private String email;
    private String username;
    private String password;
    //@OneToOne
    @OneToMany(mappedBy = "propertyOwner")
    private List<Property> propertyList;
}