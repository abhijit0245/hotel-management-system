package com.HotelManagementSystem.controller;

import com.HotelManagementSystem.dto.CreateRoomRequest;
import com.HotelManagementSystem.dto.UpdateRoomRequest;
import com.HotelManagementSystem.entity.Room;
import com.HotelManagementSystem.service.RoomService;
import com.HotelManagementSystem.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomRequest request, HttpServletRequest httpRequest) {
        validateAdmin(httpRequest);
        return ResponseEntity.ok(roomService.createRoom(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id,
                                           @RequestBody UpdateRoomRequest request,
                                           HttpServletRequest httpRequest) {
        validateAdmin(httpRequest);
        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id, HttpServletRequest httpRequest) {
        validateAdmin(httpRequest);
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    private void validateAdmin(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        if (!jwtService.extractIsAdmin(token)) {
            throw new RuntimeException("Access denied: Admin only");
        }
    }
}
