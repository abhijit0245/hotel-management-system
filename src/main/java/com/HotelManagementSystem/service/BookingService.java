package com.HotelManagementSystem.service;

import com.HotelManagementSystem.dto.CreateBookingRequest;
import com.HotelManagementSystem.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking bookRoom(CreateBookingRequest request, String userEmail);
    List<Booking> getMyBookings(String userEmail);
}
