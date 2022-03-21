package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.repository.PropertyRepairOrderRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyRepairOrderServiceImpl implements PropertyRepairOrderService{
    private PropertyRepairOrderRepository propertyRepairOrderRepository;
    private PropertyRepository propertyRepository;

    private static final String LINE_DELIMITER = "---------------------------------------";

    @Override
    public ResponseResultDto<PropertyRepairOrder> createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId){
        log.info("");
        log.info("Creating a new Property Repair Order...");
        log.info(LINE_DELIMITER);

        // Check if property exists.
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (propertyOpt.isEmpty()) {
            log.info("The property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "The property was not found.");
        }

        // Check if Property Repair Order exists.
        if (propertyRepairOrder == null) {
            log.info("The property repair order was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "The property repair order was not found.");
        }

        try {
            // Set date of registration with local date time and set the property owner.
            propertyRepairOrder.setDateOfRegistrationOrder(LocalDateTime.now());
            propertyRepairOrder.setProperty(propertyOpt.get());

            propertyRepairOrderRepository.save(propertyRepairOrder);
            log.info("Created property repair order.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(propertyRepairOrder, ResponseStatus.SUCCESS, "Created property repair order.");
        }
        catch (Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId){
        log.info("");
        log.info("Searching Property Repair Order...");
        log.info(LINE_DELIMITER);

        // List to return.
        List<PropertyRepairOrder> propertyRepairOrderList = new ArrayList<>();

        // Find property owners at the property repair orders, and add them to the list.
        try {
            for (PropertyRepairOrder propertyRepairOrder : propertyRepairOrderRepository.findAll())
                if (propertyRepairOrder.getProperty().getPropertyOwner().getPropertyOwnerId() == propertyOwnerId)
                    propertyRepairOrderList.add(propertyRepairOrder);
        }
        catch (Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
            }

        // If there are no property owner we throw a response result.
        if (propertyRepairOrderList.isEmpty()) {
            log.info("Property owners were not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "Property owners were not found.");
        }

        log.info("The property repair orders were found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyRepairOrderList, ResponseStatus.SUCCESS, "The property repair orders were found.");
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByDate(String firstDate){
        log.info("");
        log.info("Searching Property Repair Order...");
        log.info(LINE_DELIMITER);

        // Getting the list of property repair orders from query.
        String secondDate = firstDate + "T23:59:59.999";
        List<PropertyRepairOrder> propertyRepairOrderList;
        try {
            propertyRepairOrderList = propertyRepairOrderRepository.getData_between(firstDate, secondDate);
        }
        catch (Exception e){
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // If there are no property repair orders we throw a response result.
        if (propertyRepairOrderList.isEmpty()) {
            log.info("Property repair orders were not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair orders were not found.");
        }

        log.info("Property repair orders were found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyRepairOrderList, ResponseStatus.SUCCESS, "Property repair orders were found.");
    }

    @Override
    public ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(String firstDate, String secondDate){
        log.info("");
        log.info("Searching Property Repair Order...");
        log.info(LINE_DELIMITER);

        // Getting the list of property repair orders from query.
        List<PropertyRepairOrder> propertyRepairOrderList;
        try {
            propertyRepairOrderList = propertyRepairOrderRepository.getData_between(firstDate, secondDate);
        }
        catch (Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // If there are no property repair orders we throw an exception.
        if (propertyRepairOrderList.isEmpty()) {
            log.info("Property repair orders were not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair orders were not found.");
        }

        log.info("Property repair orders were found.");
        log.info(LINE_DELIMITER);
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
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder){
        // Check if body of property repair order is null.
        if (    propertyRepairOrder.getDateOfRegistrationOrder() == null && propertyRepairOrder.getDateOfScheduledRepair() == null &&
                propertyRepairOrder.getRepairStatus() == null && propertyRepairOrder.getRepairType() == null && propertyRepairOrder.getCostOfRepair() == null &&
                propertyRepairOrder.getProperty() == null && propertyRepairOrder.getDescription() == null)
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property repair order.");

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

        // Check every possible field for user input, and update it.
        try {
            if (propertyRepairOrder.getDateOfScheduledRepair() != null)
                propertyRepairOrderOpt.get().setDateOfScheduledRepair(propertyRepairOrder.getDateOfScheduledRepair());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Date of scheduled repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairStatus() != null)
                propertyRepairOrderOpt.get().setRepairStatus(propertyRepairOrder.getRepairStatus());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Repair status is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairType() != null)
                propertyRepairOrderOpt.get().setRepairType(propertyRepairOrder.getRepairType());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Repair type is incorrect.");
        }

        try {
            if (propertyRepairOrder.getCostOfRepair() != null)
                propertyRepairOrderOpt.get().setCostOfRepair(propertyRepairOrder.getCostOfRepair());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Cost of repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getDescription() != null)
                propertyRepairOrderOpt.get().setDescription(propertyRepairOrder.getDescription());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Description is incorrect.");
        }

        // Saving the property repair order to the repository and check for errors.
        try {
            propertyRepairOrderRepository.save(propertyRepairOrderOpt.get());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        return new ResponseResultDto<>(propertyRepairOrderOpt.get(), ResponseStatus.SUCCESS, "Property repair order was updated.");
    }

    @Override
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder){
        // Check if body of property repair order is null.
        if (    propertyRepairOrder.getDateOfRegistrationOrder() == null && propertyRepairOrder.getDateOfScheduledRepair() == null &&
                propertyRepairOrder.getRepairStatus() == null && propertyRepairOrder.getRepairType() == null && propertyRepairOrder.getCostOfRepair() == null &&
                propertyRepairOrder.getProperty() == null && propertyRepairOrder.getDescription() == null)
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property repair order.");

        // Check if Property exists.
        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyId);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if property was not found.
        if (propertyOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");

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

        // Check every possible field for user input, and update it.
        try {
            if (propertyRepairOrder.getDateOfScheduledRepair() != null)
                propertyRepairOrderOpt.get().setDateOfScheduledRepair(propertyRepairOrder.getDateOfScheduledRepair());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Date of scheduled repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairStatus() != null)
                propertyRepairOrderOpt.get().setRepairStatus(propertyRepairOrder.getRepairStatus());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Repair status is incorrect.");
        }

        try {
            if (propertyRepairOrder.getRepairType() != null)
                propertyRepairOrderOpt.get().setRepairType(propertyRepairOrder.getRepairType());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Repair type is incorrect.");
        }

        try {
            if (propertyRepairOrder.getCostOfRepair() != null)
                propertyRepairOrderOpt.get().setCostOfRepair(propertyRepairOrder.getCostOfRepair());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Cost of repair is incorrect.");
        }

        try {
            if (propertyRepairOrder.getDescription() != null)
                propertyRepairOrderOpt.get().setDescription(propertyRepairOrder.getDescription());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_REPAIR_ORDER_INFORMATION_ARE_INCORRECT, "Description is incorrect.");
        }

        // Saving the property repair order to the repository and check for errors.
        try {
            propertyRepairOrderOpt.get().setProperty(propertyOpt.get());
            propertyRepairOrderRepository.save(propertyRepairOrderOpt.get());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        return new ResponseResultDto<>(propertyRepairOrderOpt.get(), ResponseStatus.SUCCESS, "Property repair order was updated.");
    }

    @Override
    public ResponseResultDto<Boolean> deletePropertyRepairOrder(int propertyRepairOrderId){
        //Check if PropertyRepairOrder exists.
        Optional<PropertyRepairOrder> propertyRepairOrderDb;
        try {
            propertyRepairOrderDb = propertyRepairOrderRepository.findById(propertyRepairOrderId);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if property repair order was not found.
        if (propertyRepairOrderDb.isEmpty())
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_REPAIR_ORDER_NOT_FOUND, "Property repair order was not found.");

        // Then delete it.
        try {
            propertyRepairOrderRepository.delete(propertyRepairOrderDb.get());
        }
        catch (Exception e){
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }

        return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "Property repair order was deleted.");
    }
}
