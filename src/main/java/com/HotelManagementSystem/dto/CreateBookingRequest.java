package com.HotelManagementSystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateBookingRequest {
    private Long roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
