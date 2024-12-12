package com.TravelBooking;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@Validated
public class TravelBookingController {

    private final WorkflowClient workflowClient;

    @Autowired
    public TravelBookingController(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        // Generate a unique booking ID
        String bookingId = UUID.randomUUID().toString().substring(0, 18);

        // Configure Workflow options
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(TaskQueues.TRAVEL_BOOKING_TASK_QUEUE)
                .setWorkflowId("travelBooking-" + bookingId)
                .build();

        // Start the workflow
        TravelBookingWorkflow workflow = workflowClient.newWorkflowStub(TravelBookingWorkflow.class, options);
        WorkflowClient.start(workflow::bookTravel, bookingId);

        return ResponseEntity.ok("Booking initiated for UserID: " + bookingRequest.getUserId());
    }
}
