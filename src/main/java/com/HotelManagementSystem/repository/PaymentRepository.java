package com.HotelManagementSystem.repository;

import com.HotelManagementSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
