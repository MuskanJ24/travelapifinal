package com.TravelBooking;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userBookings")
public class UserBooking {

    @Id
    private String id;
    private String userId;
    private String destination;

    // Constructors
    public UserBooking() {}

    public UserBooking(String userId, String destination) {
        this.userId = userId;
        this.destination = destination;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
