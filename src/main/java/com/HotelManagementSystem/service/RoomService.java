package com.HotelManagementSystem.service;

import com.HotelManagementSystem.dto.CreateRoomRequest;
import com.HotelManagementSystem.dto.UpdateRoomRequest;
import com.HotelManagementSystem.entity.Room;
import java.util.List;

public interface RoomService {
    List<Room> getAvailableRooms();
    Room createRoom(CreateRoomRequest request);
    Room updateRoom(Long id, UpdateRoomRequest request);
    void deleteRoom(Long id);
}
