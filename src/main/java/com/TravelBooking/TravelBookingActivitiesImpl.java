package com.TravelBooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelBookingActivitiesImpl implements TravelBookingActivities {

    private final BookingRepository bookingRepository;

    // Manual toggle to control success or failure for testing
    private boolean shouldSucceed = true;

    // Constructor injection for repository
    @Autowired
    public TravelBookingActivitiesImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void bookHotel(String bookingId) {
        System.out.printf("Hotel booking started for BookingID: %s%n", bookingId);

        if (!shouldSucceed) {
            throw new NonRetryableActivityException("Hotel booking failed due to a non-retryable error.");
        }

        Booking booking = new Booking(bookingId, "HotelBooked");
        bookingRepository.save(booking);
        System.out.printf("Hotel booking completed for BookingID: %s%n", bookingId);
    }

    @Override
    public void bookFlight(String bookingId) {
        System.out.printf("Flight booking started for BookingID: %s%n", bookingId);

        if (!shouldSucceed) {
            throw new NonRetryableActivityException("Flight booking failed due to a non-retryable error.");
        }

        Booking booking = new Booking(bookingId, "FlightBooked");
        bookingRepository.save(booking);
        System.out.printf("Flight booking completed for BookingID: %s%n", bookingId);
    }

    @Override
    public void bookCab(String bookingId) {
        System.out.printf("Cab booking started for BookingID: %s%n", bookingId);

        if (!shouldSucceed) {
            throw new NonRetryableActivityException("Cab booking failed due to a non-retryable error.");
        }

        Booking booking = new Booking(bookingId, "CabBooked");
        bookingRepository.save(booking);
        System.out.printf("Cab booking completed for BookingID: %s%n", bookingId);
    }

    @Override
    public void sendNotification(String bookingId) {
        System.out.printf("Sending notification for BookingID: %s%n", bookingId);

        if (!shouldSucceed) {
            throw new NonRetryableActivityException("Notification sending failed due to a non-retryable error.");
        }

        Booking booking = new Booking(bookingId, "NotificationSent");
        bookingRepository.save(booking);
        System.out.printf("Notification sent for BookingID: %s%n", bookingId);
    }

    // Setter to toggle the success or failure of activities
    public void setShouldSucceed(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }
}
