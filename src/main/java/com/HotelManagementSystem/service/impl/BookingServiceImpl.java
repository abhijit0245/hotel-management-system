package com.HotelManagementSystem.service.impl;

import com.HotelManagementSystem.domain.BookingStatus;
import com.HotelManagementSystem.dto.CreateBookingRequest;
import com.HotelManagementSystem.entity.*;
import com.HotelManagementSystem.repository.*;
import com.HotelManagementSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Booking bookRoom(CreateBookingRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.isAvailable()) {
            throw new RuntimeException("Room not available");
        }

        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .status(BookingStatus.CONFIRMED) // or PENDING if manual approval
                .build();

        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getMyBookings(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }
}