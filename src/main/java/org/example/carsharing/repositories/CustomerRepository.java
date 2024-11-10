package org.example.carsharing.repositories;

import org.example.carsharing.models.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity, Integer>{
}
