package gr.codehub.accenture.technicoproject.model;

import gr.codehub.accenture.technicoproject.enumer.PropertyType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long propertyIdentificationNumber;
    private String propertyAddress;
    private Integer yearOfConstruction;
    private PropertyType propertyType;
    //@OneToOne
    @ManyToOne
    private PropertyOwner propertyOwner;
    @OneToMany(mappedBy = "property")
    private List<PropertyRepairOrder> propertyRepairOrderList;
}
