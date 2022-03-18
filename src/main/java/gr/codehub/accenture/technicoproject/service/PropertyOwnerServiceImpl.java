package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropertyOwnerServiceImpl implements PropertyOwnerService{
    private PropertyOwnerRepository propertyOwnerRepository;

    // Creating property Owner
    public PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) {
        return propertyOwnerRepository.save(propertyOwner);
    }
}
