package org.example.carsharing.repositories;

import org.example.carsharing.models.BookingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity, Integer>{
}
