package org.example.carsharing.repositories;

import org.example.carsharing.models.CarEntity;
import org.example.carsharing.models.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity, Long>{
//    CustomerEntity findByDriverLicense(String driverLicense);
    Optional<CustomerEntity> findByNumber(String number);
//    List<CustomerEntity> findByBookingsStatus(String status);
//    @Query("SELECT ")
    List<CustomerEntity> findAll();
    @Query("select c from CustomerEntity c where c.id = :customerId")
    CustomerEntity findById(Long customerId);
}
