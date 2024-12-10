package com.TravelBooking;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
    // Custom query methods can be added here if required
}
