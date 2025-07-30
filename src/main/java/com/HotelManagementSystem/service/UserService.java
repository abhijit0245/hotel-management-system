package com.HotelManagementSystem.service;

import com.HotelManagementSystem.dto.AuthRequest;
import com.HotelManagementSystem.dto.AuthResponse;
import com.HotelManagementSystem.dto.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}

