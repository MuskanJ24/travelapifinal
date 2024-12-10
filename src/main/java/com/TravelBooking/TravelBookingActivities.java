package com.TravelBooking;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface TravelBookingActivities {

    void bookHotel(String bookingId);

    void bookFlight(String bookingId);

    void bookCab(String bookingId);

    void sendNotification(String bookingId);
}