package com.TravelBooking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface BookingRepository extends MongoRepository<Booking, String> {
    // Custom query methods can be added here if required
}
