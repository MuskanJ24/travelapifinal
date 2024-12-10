package com.TravelBooking;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class TravelBookingApp {

    public static void main(String[] args) {
        SpringApplication.run(TravelBookingApp.class, args);

        System.out.println("Welcome to the Travel Booking System!");

        // Initialize Temporal service stub
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newLocalServiceStubs();

        // Create Temporal Workflow client
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        // Generate a unique workflow ID using UUID
        String uniqueWorkflowId = "travelBooking-" + UUID.randomUUID();

// Configure Workflow options with the unique ID
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(TaskQueues.TRAVEL_BOOKING_TASK_QUEUE)
                .setWorkflowId(uniqueWorkflowId) // Use unique ID
                .build();


        // Create Workflow stub
        TravelBookingWorkflow workflow = client.newWorkflowStub(TravelBookingWorkflow.class, options);

        // Generate a unique booking ID
        String bookingId = UUID.randomUUID().toString().substring(0, 18);

        // Start the Workflow asynchronously
        WorkflowExecution we = WorkflowClient.start(workflow::bookTravel, bookingId);

        // Log Workflow details
        System.out.printf("\nTravel Booking Process Initiating...\n\n");
        System.out.printf("Booking initiated for [BookingID %s].\n\n", bookingId);
        System.out.printf("[WorkflowID: %s]\n[RunID: %s]\n\n", we.getWorkflowId(), we.getRunId());
    }
}
