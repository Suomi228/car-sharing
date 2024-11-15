package org.example.carsharing.repositories;

import org.example.carsharing.models.PaymentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends BaseRepository<PaymentEntity, Long>{
//    List<PaymentEntity> findByBooking_Id(Long bookingId);
//    List<PaymentEntity> findByPaymentStatus(String status);
    List<PaymentEntity> findAll();
}
