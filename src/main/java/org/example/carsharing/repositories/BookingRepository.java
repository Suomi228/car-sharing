package org.example.carsharing.repositories;

import org.example.carsharing.models.BookingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity, Long>{
    List<BookingEntity> findByCustomerId(Long customerId);
    List<BookingEntity> findByStatus(String status);
    List<BookingEntity> findByCarIdAndStatus(Long carId, String status);
}
