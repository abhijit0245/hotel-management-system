package com.HotelManagementSystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponse {
    private Long bookingId;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
