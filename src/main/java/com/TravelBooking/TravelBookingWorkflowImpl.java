package com.TravelBooking;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class TravelBookingWorkflowImpl implements TravelBookingWorkflow {

    private final TravelBookingActivities activities = Workflow.newActivityStub(
            TravelBookingActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(2))
                    .build()
    );

    @Override
    public void bookTravel(String bookingId) {
        System.out.printf("Travel booking workflow started for BookingID: %s%n", bookingId);

        try {
            // Hotel booking
            System.out.println("Starting hotel booking...");
            activities.bookHotel(bookingId);
            System.out.println("Hotel booking completed.");

            // Flight booking
            System.out.println("Starting flight booking...");
            activities.bookFlight(bookingId);
            System.out.println("Flight booking completed.");

            // Cab booking
            System.out.println("Starting cab booking...");
            activities.bookCab(bookingId);
            System.out.println("Cab booking completed.");

            // Notification
            System.out.println("Sending notification...");
            activities.sendNotification(bookingId);
            System.out.println("Notification sent.");
        } catch (Exception e) {
            System.err.printf("An error occurred during the travel booking workflow for BookingID: %s. Error: %s%n", bookingId, e.getMessage());
            // Optionally handle the error, such as updating the status in the database or compensating for partial bookings.
        }

        System.out.println("Travel booking workflow completed.");
    }
}
