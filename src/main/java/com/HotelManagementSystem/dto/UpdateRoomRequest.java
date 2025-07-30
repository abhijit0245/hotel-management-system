package com.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private String type;
    private double price;
    private boolean isAvailable;
}
