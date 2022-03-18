package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Integer> {
}
