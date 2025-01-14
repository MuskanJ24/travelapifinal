package com.TravelBooking;

public class NonRetryableActivityException extends RuntimeException {
    public NonRetryableActivityException(String message) {
        super(message);
    }
}
