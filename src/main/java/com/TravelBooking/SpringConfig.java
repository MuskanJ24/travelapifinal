package com.TravelBooking;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.TravelBooking")
public class SpringConfig {

    /**
     * Bean to configure Temporal WorkflowServiceStubs.
     * This connects the application to the Temporal service.
     *
     * @return WorkflowServiceStubs instance
     */
    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        // Configure Temporal service stub for communication
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    /**
     * Bean to configure Temporal WorkflowClient.
     * This is used to interact with workflows.
     *
     * @param workflowServiceStubs Temporal service stubs
     * @return WorkflowClient instance
     */
    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        // Create WorkflowClient using the service stubs
        return WorkflowClient.newInstance(workflowServiceStubs);
    }
}
