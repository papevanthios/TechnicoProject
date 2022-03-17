package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepairOrderRepository extends JpaRepository<PropertyRepairOrder, Long> {
}
