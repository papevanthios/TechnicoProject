package gr.codehub.accenture.technicoproject.model;

import gr.codehub.accenture.technicoproject.enumer.RepairStatus;
import gr.codehub.accenture.technicoproject.enumer.RepairType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class PropertyRepairOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseId;
    @Column(nullable = false)
    private LocalDateTime dateOfRegistrationOrder;
    @Column(nullable = false)
    private LocalDateTime dateOfScheduledRepair;
    @Column(nullable = false)
    private RepairStatus repairStatus;
    @Column(nullable = false)
    private RepairType repairType;
    @Column(nullable = false)
    private BigDecimal costOfRepair;
    @ManyToOne
    private Property property;
    private String description;
}
