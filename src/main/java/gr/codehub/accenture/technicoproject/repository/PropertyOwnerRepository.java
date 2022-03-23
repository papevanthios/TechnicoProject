package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Integer> {

    PropertyOwner findByPropertyOwnerIdEquals(int propertyOwnerId);
    PropertyOwner findByVatNumber(String vatNumber);
    PropertyOwner findByPhoneNumber(String phoneNumber);
    PropertyOwner findByEmail(String email);
}
