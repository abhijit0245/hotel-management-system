package com.HotelManagementSystem.dto;


import lombok.Data;

@Data
public class CreateRoomRequest {
    private int number;
    private String type;
    private double price;
    private boolean isAvailable;



}

