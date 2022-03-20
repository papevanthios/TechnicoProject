package gr.codehub.accenture.technicoproject.model;


import gr.codehub.accenture.technicoproject.enumer.RepairStatus;
import gr.codehub.accenture.technicoproject.enumer.RepairType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class PropertyRepairOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseId;
    private LocalDateTime dateOfRegistrationOrder;
    private String address; // propertyAddress from property
    private Date dateOfScheduledRepair;
    private RepairStatus repairStatus;
    private RepairType repairType;
    private BigDecimal costOfRepair;
    @OneToOne
    private PropertyOwner propertyOwner; // better with property?
    private String description;
}
