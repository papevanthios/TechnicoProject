package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepairOrderRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepository;
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
    private PropertyRepository propertyRepository;

    @Override
    public ResponseResultDto<Boolean> createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId){
        // Check if property exists.
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (propertyOpt.isEmpty())
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_NOT_FOUND, "The property was not found.");

        // Check if Property Repair Order exists.
        if (propertyRepairOrder == null)
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "The property repair order was not found.");

        try {
            // Set date of registration with local date time and set the property owner.
            propertyRepairOrder.setDateOfRegistrationOrder(LocalDateTime.now());
            propertyRepairOrder.setProperty(propertyOpt.get());

            propertyRepairOrderRepository.save(propertyRepairOrder);
            return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "Created property repair order.");
        }
        catch (Exception e) {
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_REPAIR_ORDER_CANNOT_BE_CREATED, "The property repair order cannot be created.");
        }
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId){
        // List to return.
        List<PropertyRepairOrder> propertyRepairOrderList = new ArrayList<>();

        // Find property owners at the property repair orders, and add them to the list.
        for (PropertyRepairOrder propertyRepairOrder : propertyRepairOrderRepository.findAll())
            if (propertyRepairOrder.getProperty().getPropertyOwner().getPropertyOwnerId() == propertyOwnerId) {
                try {
                    propertyRepairOrderList.add(propertyRepairOrder);
                }
                catch (Exception e) {
                    return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
                }
            }

        // If there are no property owner we throw a response result.
        if (propertyRepairOrderList.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "Property owners were not found.");

        return new ResponseResultDto<>(propertyRepairOrderList, ResponseStatus.SUCCESS, "The property repair orders were found.");
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByDate(String firstDate){
        // Getting the list of property repair orders from query.
        String secondDate = firstDate + "T23:59:59.999";
        List<PropertyRepairOrder> propertyRepairOrderList;
        try {
            propertyRepairOrderList = propertyRepairOrderRepository.getData_between(firstDate, secondDate);
        }
        catch (Exception e){
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // If there are no property repair orders we throw a response result.
        if (propertyRepairOrderList.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair orders were not found.");

        return new ResponseResultDto<>(propertyRepairOrderList, ResponseStatus.SUCCESS, "Property repair orders were found.");
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(String firstDate, String secondDate){
        // Getting the list of property repair orders from query.
        List<PropertyRepairOrder> propertyRepairOrderList;
        try {
            propertyRepairOrderList = propertyRepairOrderRepository.getData_between(firstDate, secondDate);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // If there are no property repair orders we throw an exception.
        if (propertyRepairOrderList.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair orders were not found.");

        return new ResponseResultDto<>(propertyRepairOrderList, ResponseStatus.SUCCESS, "Property repair orders were found.");
    }

    @Override
    public ResponseResultDto<PropertyRepairOrder> searchByPropertyRepairOrderId(int propertyRepairOrderId) {
        // Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderOpt;
        try{
            propertyRepairOrderOpt = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if the property repair order was not found.
        if (propertyRepairOrderOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair order was not found.");

        return new ResponseResultDto<>(propertyRepairOrderOpt.get(), ResponseStatus.SUCCESS, "Property repair order was found.");
    }

    @Override
    public PropertyRepairOrder updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        // Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderOpt = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        if (propertyRepairOrderOpt.isEmpty())
            throw new PropertyRepairOrderException("The Property Repair Order can not be found.");

        // Check every possible field for user input, and update it.
        try {
            if (propertyRepairOrder.getDateOfScheduledRepair() != null)
                propertyRepairOrderOpt.get().setDateOfScheduledRepair(propertyRepairOrder.getDateOfScheduledRepair());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The date of scheduled repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairStatus() != null)
                propertyRepairOrderOpt.get().setRepairStatus(propertyRepairOrder.getRepairStatus());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The repair status is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairType() != null)
                propertyRepairOrderOpt.get().setRepairType(propertyRepairOrder.getRepairType());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The repair type is incorrect.");
        }

        try {
            if (propertyRepairOrder.getCostOfRepair() != null)
                propertyRepairOrderOpt.get().setCostOfRepair(propertyRepairOrder.getCostOfRepair());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The cost of repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getDescription() != null)
                propertyRepairOrderOpt.get().setDescription(propertyRepairOrder.getDescription());
        }
        catch (Exception e) {
            throw new PropertyRepairOrderException("The description is incorrect.");
        }

        return propertyRepairOrderRepository.save(propertyRepairOrderOpt.get());
    }

    @Override
    public PropertyRepairOrder updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        // Check if Property exists.
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (propertyOpt.isEmpty())
            throw new PropertyRepairOrderException("The Property can not be found.");

        // Check update property repair order fields and property repair id.
        PropertyRepairOrder propertyRepairOrderUpd = updatePropertyRepairOrderFields(propertyRepairOrderId, propertyRepairOrder);

        // Setting property if exists.
        propertyRepairOrderUpd.setProperty(propertyOpt.get());

        return propertyRepairOrderRepository.save(propertyRepairOrderUpd);
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
