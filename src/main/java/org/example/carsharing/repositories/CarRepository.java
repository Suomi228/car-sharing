package org.example.carsharing.repositories;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.models.CarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<CarEntity, Long>{
    List<CarEntity> findByStatusAndDeletedIsFalse(CarStatus status);
    List<CarEntity> findByCarClassAndStatusAndDeletedIsFalse(CarClass carClass, CarStatus carStatus);
    List<CarEntity> findAllByCarClassAndDeletedIsFalse(CarClass carClass);
    List<CarEntity> findAllByDeletedIsFalse();
    @Query("select c from CarEntity c where c.id = :carId")
    CarEntity findById(Long carId);
}
