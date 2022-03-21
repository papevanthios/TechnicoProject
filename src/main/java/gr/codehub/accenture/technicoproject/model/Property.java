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
    @Column(unique=true, nullable = false)
    private Long propertyIdentificationNumber;
    @Column(nullable = false)
    private String propertyAddress;
    @Column(nullable = false)
    private Integer yearOfConstruction;
    @Column(nullable = false)
    private PropertyType propertyType;
    @ManyToOne
    //@JoinColumn(, )
    private PropertyOwner propertyOwner;
    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<PropertyRepairOrder> propertyRepairOrderList;
}
