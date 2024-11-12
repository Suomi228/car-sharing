package org.example.carsharing.repositories;

import org.example.carsharing.models.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity, Long>{
//    CustomerEntity findByDriverLicense(String driverLicense);
//    CustomerEntity findByNumber(String number);
//    List<CustomerEntity> findByBookingsStatus(String status);
//    @Query("SELECT ")
    List<CustomerEntity> findAll();
}
