package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository that extends the JPA repository for Property.
 * JPA specific extension of org.springframework.data.repository.Repository.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Property findByPropertyIdEquals(int propertyId);
    Property findByPropertyIdentificationNumber(Long propertyIdentificationNumber);
}
