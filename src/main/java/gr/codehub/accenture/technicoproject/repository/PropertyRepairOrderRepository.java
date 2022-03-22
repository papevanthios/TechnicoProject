package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository that extends the JPA repository for PropertyRepairOrder.
 * JPA specific extension of org.springframework.data.repository.Repository.
 */
@Repository
public interface PropertyRepairOrderRepository extends JpaRepository<PropertyRepairOrder, Integer> {

    /**
     * Get two strings of dates to search the database for property repair orders that are between these dates.
     * @param date a string of date
     * @param date2 a string of date
     * @return a list of objects of property repair order
     */
    @Query(nativeQuery = true, value="select * from property_repair_order pr where pr.date_of_registration_order between :startDate and :endDate")
    List<PropertyRepairOrder> getData_between(@Param("startDate") String date, @Param("endDate") String date2);
}
