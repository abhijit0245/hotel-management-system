package com.HotelManagementSystem.service;

import com.HotelManagementSystem.dto.PaymentRequest;
import com.HotelManagementSystem.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(String email, PaymentRequest request);
}
