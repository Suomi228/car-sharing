package org.example.carsharing.repositories;

import org.example.carsharing.models.CarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<CarEntity, Long>{
//    List<CarEntity> findByStatus(String status);
//    List<CarEntity> findByCarClass(String carClass);
    List<CarEntity> findAll();
}
