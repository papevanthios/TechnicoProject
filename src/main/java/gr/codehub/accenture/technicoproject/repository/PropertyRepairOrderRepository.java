package gr.codehub.accenture.technicoproject.repository;

import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepairOrderRepository extends JpaRepository<PropertyRepairOrder, Integer> {

    @Query(nativeQuery = true, value="select * from property_repair_order pr where pr.date_of_registration_order between :startDate and :endDate")
    List<PropertyRepairOrder> getData(@Param("startDate") String date, @Param("endDate") String date2);

    @Query(nativeQuery = true, value="select * from property_repair_order pr where pr.date_of_registration_order between :startDate and :endDate")
    List<PropertyRepairOrder> getData_between(@Param("startDate") String date, @Param("endDate") String date2);
}
