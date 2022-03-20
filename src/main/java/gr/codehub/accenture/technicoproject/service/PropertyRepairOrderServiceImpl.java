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
    public List<PropertyRepairOrder> searchByRangeOfDates(LocalDateTime firstDate, LocalDateTime secondDate) throws PropertyRepairOrderException {
        List<PropertyRepairOrder> propertyRepairOrderList = new ArrayList<>();
        for (PropertyRepairOrder propertyRepairOrder : propertyRepairOrderRepository.findAll())
            if (propertyRepairOrder.getDateOfRegistrationOrder().isAfter(firstDate) && propertyRepairOrder.getDateOfRegistrationOrder().isBefore(secondDate))
                propertyRepairOrderList.add(propertyRepairOrder);
        if (propertyRepairOrderList.isEmpty())
            throw new PropertyRepairOrderException("No property repair orders found.");
        return propertyRepairOrderList;
    }

    @Override
    public PropertyRepairOrder updatePropertyRepairOrder(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        // Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderDb = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        if (propertyRepairOrderDb.isEmpty())
            throw new PropertyRepairOrderException("The Property Repair Order can not be found.");

        // Check every possible field for user input, and update it.
        try {
            if (propertyRepairOrder.getAddress() != null)
                propertyRepairOrderDb.get().setAddress(propertyRepairOrder.getAddress());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The address is incorrect.");
        }

        try {
            if (propertyRepairOrder.getDateOfScheduledRepair() != null)
                propertyRepairOrderDb.get().setDateOfScheduledRepair(propertyRepairOrder.getDateOfScheduledRepair());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The date of scheduled repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairStatus() != null)
                propertyRepairOrderDb.get().setRepairStatus(propertyRepairOrder.getRepairStatus());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The repair status is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairType() != null)
                propertyRepairOrderDb.get().setRepairType(propertyRepairOrder.getRepairType());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The repair type is incorrect.");
        }

        try {
            if (propertyRepairOrder.getCostOfRepair() != null)
                propertyRepairOrderDb.get().setCostOfRepair(propertyRepairOrder.getCostOfRepair());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The cost of repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getDescription() != null)
                propertyRepairOrderDb.get().setDescription(propertyRepairOrder.getDescription());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The description is incorrect.");
        }

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
