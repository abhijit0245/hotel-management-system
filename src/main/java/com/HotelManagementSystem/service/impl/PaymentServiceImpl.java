package com.HotelManagementSystem.service.impl;

import com.HotelManagementSystem.dto.PaymentRequest;
import com.HotelManagementSystem.dto.PaymentResponse;
import com.HotelManagementSystem.entity.Booking;
import com.HotelManagementSystem.entity.Payment;
import com.HotelManagementSystem.entity.User;
import com.HotelManagementSystem.repository.BookingRepository;
import com.HotelManagementSystem.repository.PaymentRepository;
import com.HotelManagementSystem.repository.UserRepository;
import com.HotelManagementSystem.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaymentResponse processPayment(String email, PaymentRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        String status;

        // Simulate payment (you can later replace with real Razorpay call)
        if (request.getCardNumber().startsWith("4")) {
            status = "SUCCESS";
        } else {
            status = "FAILED";
        }

        Payment payment = Payment.builder()
                .userId(user.getId())
                .bookingId(booking.getId())
                .amount(request.getAmount())
                .status(status)
                .build();

        paymentRepository.save(payment);

        return new PaymentResponse(status, status.equals("SUCCESS") ?
                "Payment processed successfully" :
                "Payment failed. Invalid card.");
    }
}
