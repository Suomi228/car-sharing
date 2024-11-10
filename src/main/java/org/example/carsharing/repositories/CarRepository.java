package org.example.carsharing.repositories;

import org.example.carsharing.models.CarEntity;
import org.example.carsharing.models.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends BaseRepository<CarEntity, Integer>{
}
