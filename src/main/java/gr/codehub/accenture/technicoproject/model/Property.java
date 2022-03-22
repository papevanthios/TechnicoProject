package gr.codehub.accenture.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.codehub.accenture.technicoproject.enumer.PropertyType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represent a property object.
 */
@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;

    @NotNull(message = "PropertyIdentificationNumber cannot be null")
    @Column(unique = true)
    private Long propertyIdentificationNumber;

    @NotNull(message = "PropertyAddress cannot be null")
    @Column()
    private String propertyAddress;

    @NotNull(message = "YearOfConstruction cannot be null")
    @Column
    private Integer yearOfConstruction;

    @NotNull(message = "PropertyType cannot be null")
    @Column
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "propertyOwnerId", referencedColumnName = "propertyOwnerId")
    private PropertyOwner propertyOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<PropertyRepairOrder> propertyRepairOrderList;
}
