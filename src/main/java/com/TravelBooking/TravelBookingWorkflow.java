package com.TravelBooking;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TravelBookingWorkflow {

    @WorkflowMethod
    void bookTravel(String bookingId);
}