package com.HotelManagementSystem.repository;

import com.HotelManagementSystem.entity.Booking;
import com.HotelManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
