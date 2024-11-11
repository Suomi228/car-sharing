package org.example.carsharing.repositories;

import org.example.carsharing.models.DamageReportEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DamageReportRepository extends BaseRepository<DamageReportEntity, Long>{
    List<DamageReportEntity> findByCar_Id(Long carId);

    List<DamageReportEntity> findByDiscoveryDateBetween(String startDate, String endDate);
    List<DamageReportEntity> findByRepairPriceGreaterThanAndRepairPriceIsNull(double minRepairCost);
}
