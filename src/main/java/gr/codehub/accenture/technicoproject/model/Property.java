package gr.codehub.accenture.technicoproject.model;

import gr.codehub.accenture.technicoproject.enumer.PropertyType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyIdentificationNumber;

    private String propertyAddress;

    private Integer yearOfConstruction;

    private PropertyType propertyType;

    @OneToOne
    private PropertyOwner propertyOwner;

}
