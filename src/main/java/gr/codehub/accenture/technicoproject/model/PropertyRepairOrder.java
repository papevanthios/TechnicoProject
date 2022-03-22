package gr.codehub.accenture.technicoproject.model;

import gr.codehub.accenture.technicoproject.enumer.RepairStatus;
import gr.codehub.accenture.technicoproject.enumer.RepairType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represent a property repair order object.
 */
@Data
@Entity
public class PropertyRepairOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseId;

    @Column(nullable = false)
    private LocalDateTime dateOfRegistrationOrder;

    @NotNull(message = "DateOfScheduledRepair cannot be null")
    @Column
    private LocalDateTime dateOfScheduledRepair;

    @NotNull(message = "RepairStatus cannot be null")
    @Column
    private RepairStatus repairStatus;

    @NotNull(message = "RepairType cannot be null")
    @Column
    private RepairType repairType;

    @NotNull(message = "CostOfRepair cannot be null")
    @Column
    private BigDecimal costOfRepair;

    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    private String description;
}
