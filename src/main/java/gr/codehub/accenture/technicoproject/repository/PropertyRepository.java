package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Property
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Property findByPropertyIdentificationNumber(Long propertyIdentificationNumber);
}
