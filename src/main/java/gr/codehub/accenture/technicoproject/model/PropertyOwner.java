package gr.codehub.accenture.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Represent a property owner object.
 */
@Data
@Entity
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyOwnerId;

    @NotNull(message = "VatNumber cannot be null")
    @Column(unique=true, length = 9)
    @Size(min=9, max=9, message = "The vatNumber '${validatedValue}' must be at least {min} characters long and at most {max} characters long.")
    private String vatNumber;

    @NotNull(message = "FirstName cannot be null")
    @Column
    private String firstName;

    @NotNull(message = "LastName cannot be null")
    @Column
    private String lastName;

    @NotNull(message = "Address cannot be null")
    @Column
    private String address;

    @NotNull(message = "PhoneNumber cannot be null")
    @Column(unique=true, length = 10)
    @Size(min = 10, max = 10, message ="The phoneNumber '${validatedValue}' must be at least {min} characters long and at most {max} characters long.")
    private String phoneNumber;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotNull(message = "Email cannot be null")
    @Column
    private String email;

    @NotNull(message = "Username cannot be null")
    @Column
    private String username;

    @NotNull(message = "Password cannot be null")
    @Column
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner")
    private List<Property> propertyList;
}