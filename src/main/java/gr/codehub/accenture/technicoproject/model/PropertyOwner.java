package gr.codehub.accenture.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int propertyOwnerId;
    @Column(unique=true, length = 9)
    @Size(min=9, max=9)
    private String vatNumber;
    private String firstName;
    private String lastName;
    private String address;
    @Column(unique=true, length = 10)
    @Size(min=10, max=10)
    private String phoneNumber;
    @Email
    private String email;
    private String username;
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner")
    private List<Property> propertyList;
}