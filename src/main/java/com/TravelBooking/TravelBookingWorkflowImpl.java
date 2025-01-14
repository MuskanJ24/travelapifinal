package com.TravelBooking;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class TravelBookingWorkflowImpl implements TravelBookingWorkflow {

    // Configure RetryOptions
    private final TravelBookingActivities activities = Workflow.newActivityStub(
            TravelBookingActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(2)) // Max time for each activity
                    .setRetryOptions(
                            RetryOptions.newBuilder()
                                    .setMaximumAttempts(5) // Max number of retries
                                    .setInitialInterval(Duration.ofSeconds(2)) // Initial retry delay
                                    .setBackoffCoefficient(2.0) // Exponential backoff
                                    .setMaximumInterval(Duration.ofSeconds(10)) // Max delay between retries
                                    .setDoNotRetry(NonRetryableActivityException.class.getName()) // Non-retryable exception
                                    .build()
                    )
                    .build()
    );

    @Override
    public void bookTravel(String bookingId) {
        System.out.printf("Travel booking workflow started for BookingID: %s%n", bookingId);

        try {
            // Call the activities
            System.out.println("Starting hotel booking...");
            activities.bookHotel(bookingId);
            System.out.println("Hotel booking completed.");

            System.out.println("Starting flight booking...");
            activities.bookFlight(bookingId);
            System.out.println("Flight booking completed.");

            System.out.println("Starting cab booking...");
            activities.bookCab(bookingId);
            System.out.println("Cab booking completed.");

            System.out.println("Sending notification...");
            activities.sendNotification(bookingId);
            System.out.println("Notification sent.");
        } catch (NonRetryableActivityException e) {
            // Mark workflow as failed by rethrowing the exception
            System.err.printf("Workflow failed due to a non-retryable error: %s%n", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Handle unexpected exceptions and fail the workflow
            System.err.printf("Unexpected error occurred: %s%n", e.getMessage());
            throw e;
        }

        System.out.println("Travel booking workflow completed.");
    }
}
