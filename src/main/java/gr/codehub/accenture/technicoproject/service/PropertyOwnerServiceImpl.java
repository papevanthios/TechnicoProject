package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class PropertyOwnerServiceImpl implements PropertyOwnerService{
    private PropertyOwnerRepository propertyOwnerRepository;

    // Creating property Owner

    @Override
    public PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) {
        return propertyOwnerRepository.save(propertyOwner);
    }

    // property owner search1 maria

    @Override
    public PropertyOwner searchByVAT(int propertyOwnerVAT) {
        return propertyOwnerRepository.findById(propertyOwnerVAT).get();
    }
    // property owner search2 maria
//
//
//    @Override
//    public PropertyOwner searchByEmail(int propertyOwnerEmail) {
//        return propertyOwnerRepository.findById(propertyOwnerEmail).get();
//    }




        // property owner update aris

        // property owner delete aris
}