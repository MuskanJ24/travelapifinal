package com.TravelBooking;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBookingRepository extends MongoRepository<UserBooking, String> {
}
