package com.TravelBooking;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BookingRequest {
    @NotEmpty(message = "User ID cannot be empty")
    private String userId;

    @NotEmpty(message = "Destination cannot be empty")
    @Size(max = 50, message = "Destination cannot exceed 50 characters")
    private String destination;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
