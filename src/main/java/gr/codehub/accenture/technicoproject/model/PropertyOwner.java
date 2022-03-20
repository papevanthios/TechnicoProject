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
    @Column(unique=true, length = 9, nullable = false)
    @Size(min=9, max=9)
    private String vatNumber;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(unique=true, length = 10, nullable = false)
    @Size(min=10, max=10)
    private String phoneNumber;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner")
    private List<Property> propertyList;
}