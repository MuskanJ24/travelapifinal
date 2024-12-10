package com.TravelBooking;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TravelBookingWorker {

    public static void main(String[] args) {
        // Create Temporal service stubs
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Create a WorkflowClient
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Create a WorkerFactory using the WorkflowClient
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TaskQueues.TRAVEL_BOOKING_TASK_QUEUE);

        // Initialize Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Register workflow and activity implementations
        worker.registerWorkflowImplementationTypes(TravelBookingWorkflowImpl.class);
        worker.registerActivitiesImplementations(context.getBean(TravelBookingActivitiesImpl.class));

        // Start the factory
        factory.start();

        System.out.println("Worker started for task queue: " + TaskQueues.TRAVEL_BOOKING_TASK_QUEUE);
    }
}
