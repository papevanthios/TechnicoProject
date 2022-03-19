package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepairOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyRepairOrderServiceImpl implements PropertyRepairOrderService{
    private PropertyRepairOrderRepository propertyRepairOrderRepository;
    private PropertyOwnerRepository propertyOwnerRepository;

    @Override
    public PropertyRepairOrder createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyOwnerId) throws PropertyRepairOrderException {
        // Check if PropertyOwner exists.
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            throw new PropertyRepairOrderException("The Property Owner does not exist.");

        // Check if Property Repair Order information are not null.
        if (    propertyRepairOrder == null
                || propertyRepairOrder.getAddress() == null
                || propertyRepairOrder.getDateOfScheduledRepair() == null
                || propertyRepairOrder.getRepairStatus() == null
                || propertyRepairOrder.getRepairType() == null
                || propertyRepairOrder.getCostOfRepair() == null
            )
            throw new PropertyRepairOrderException("Missing Property Repair Order information.");

        // Set date of registration with local date time and set the property owner.
        propertyRepairOrder.setDateOfRegistrationOrder(LocalDateTime.now());
        propertyRepairOrder.setPropertyOwner(propertyOwnerOpt.get());

        return propertyRepairOrderRepository.save(propertyRepairOrder);
    }

    @Override
    public List<PropertyRepairOrder> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId) throws PropertyRepairOrderException {
        // List to return.
        List<PropertyRepairOrder> propertyRepairOrderList = new ArrayList<>();

        // Iterate through all the repository or property repair order and add them to the list.
        for (PropertyRepairOrder propertyRepairOrder: propertyRepairOrderRepository.findAll()) {
            if (propertyRepairOrder.getPropertyOwner().getId() == propertyOwnerId) {
                propertyRepairOrderList.add(propertyRepairOrder);
            }
        }

        // If list is empty then there are no property owners to be found. We throw a custom exception.
        if (propertyRepairOrderList.isEmpty())
            throw new PropertyRepairOrderException("No Property Owners with that id has be found.");
        return propertyRepairOrderList;
    }

    @Override
    public List<PropertyRepairOrder> searchByRangeOfDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        List<PropertyRepairOrder> propertyRepairOrderList = new ArrayList<>();
//        for (LocalDateTime d = firstDate;  !d.isAfter(secondDate) ; d = d.plusDays(1)) {
//            propertyRepairOrderList.add(d);
//        }
        return null;
    }

    @Override
    public PropertyRepairOrder updatePropertyRepairOrder(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        // Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderDb = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        if (propertyRepairOrderDb.isEmpty())
            throw new PropertyRepairOrderException("The Property Repair Order can not be found.");

        // Check every possible field for user input, and update it.
        if (propertyRepairOrder.getAddress() != null)
            propertyRepairOrderDb.get().setAddress(propertyRepairOrder.getAddress());

        if (propertyRepairOrder.getDateOfScheduledRepair() != null)
            propertyRepairOrderDb.get().setDateOfScheduledRepair(propertyRepairOrder.getDateOfScheduledRepair());

        if (propertyRepairOrder.getRepairStatus() != null)
            propertyRepairOrderDb.get().setRepairStatus(propertyRepairOrder.getRepairStatus());

        if (propertyRepairOrder.getRepairType() != null)
            propertyRepairOrderDb.get().setRepairType(propertyRepairOrder.getRepairType());

        if (propertyRepairOrder.getCostOfRepair() != null)
            propertyRepairOrderDb.get().setCostOfRepair(propertyRepairOrder.getCostOfRepair());

        if (propertyRepairOrder.getDescription() != null)
            propertyRepairOrderDb.get().setDescription(propertyRepairOrder.getDescription());

        return propertyRepairOrderRepository.save(propertyRepairOrderDb.get());
    }

    @Override
    public boolean deletePropertyRepairOrder(int propertyRepairOrderId) throws PropertyRepairOrderException {
        //Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderDb = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        if (propertyRepairOrderDb.isEmpty())
            throw new PropertyRepairOrderException("The Property Repair Order can not be found.");

        // Then delete it.
        propertyRepairOrderRepository.delete(propertyRepairOrderDb.get());
        return true;
    }
}
