package org.example.carsharing.repositories;

import org.example.carsharing.models.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<PaymentEntity, Integer>{
}
