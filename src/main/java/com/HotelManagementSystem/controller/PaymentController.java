package com.HotelManagementSystem.controller;

import com.HotelManagementSystem.dto.PaymentRequest;
import com.HotelManagementSystem.dto.PaymentResponse;
import com.HotelManagementSystem.service.PaymentService;
import com.HotelManagementSystem.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JwtService jwtService;

    private String extractEmailFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.replace("Bearer ", "") : null;
        return jwtService.extractUsername(token);
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request, HttpServletRequest httpServletRequest) {
        String email = extractEmailFromToken(httpServletRequest);
        return ResponseEntity.ok(paymentService.processPayment(email, request));
    }
}
