package org.example.carsharing.repositories;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.models.CarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<CarEntity, Long>{
    List<CarEntity> findByStatus(CarStatus status);
    List<CarEntity> findByCarClassAndStatus(CarClass carClass, CarStatus carStatus);
    List<CarEntity> findAll();
    @Query("select c from CarEntity c where c.id = :carId")
    CarEntity findById(Long carId);
}
