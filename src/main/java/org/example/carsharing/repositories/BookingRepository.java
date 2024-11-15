package org.example.carsharing.repositories;

import org.example.carsharing.models.BookingEntity;
import org.example.carsharing.models.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity, Long> {
    List<BookingEntity> findByCustomerId(Long customerId);
//    List<BookingEntity> findByStatus(String status);
//    List<BookingEntity> findByCarIdAndStatus(Long carId, String status);
    @Query("SELECT b from BookingEntity b")
    List<BookingEntity> findAll();
    @Query("select c from BookingEntity c where c.id = :carId")
    BookingEntity findById(Long carId);
}
