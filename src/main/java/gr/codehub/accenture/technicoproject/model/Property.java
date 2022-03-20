package gr.codehub.accenture.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.codehub.accenture.technicoproject.enumer.PropertyType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;
    private Long propertyIdentificationNumber;
    private String propertyAddress;
    private Integer yearOfConstruction;
    private PropertyType propertyType;
    @ManyToOne
    private PropertyOwner propertyOwner;
    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<PropertyRepairOrder> propertyRepairOrderList;
}
