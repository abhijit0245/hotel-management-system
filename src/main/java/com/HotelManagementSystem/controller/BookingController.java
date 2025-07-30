package com.HotelManagementSystem.controller;

import com.HotelManagementSystem.dto.CreateBookingRequest;
import com.HotelManagementSystem.entity.Booking;
import com.HotelManagementSystem.service.BookingService;
import com.HotelManagementSystem.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtService jwtService;

    private String extractEmailFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader != null ? authHeader.replace("Bearer ", "") : null;
        return jwtService.extractUsername(token);
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> bookRoom(@RequestBody CreateBookingRequest request, HttpServletRequest httpServletRequest) {
        String email = extractEmailFromToken(httpServletRequest);
        return ResponseEntity.ok(bookingService.bookRoom(request, email));
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<Booking>> getMyBookings(HttpServletRequest httpServletRequest) {
        String email = extractEmailFromToken(httpServletRequest);
        return ResponseEntity.ok(bookingService.getMyBookings(email));
    }
}
