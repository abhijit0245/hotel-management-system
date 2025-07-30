package com.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long bookingId;
    private double amount;
    private String cardNumber; // simulate
}
