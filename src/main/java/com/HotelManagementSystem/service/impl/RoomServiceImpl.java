package com.HotelManagementSystem.service.impl;

import com.HotelManagementSystem.dto.CreateRoomRequest;
import com.HotelManagementSystem.dto.UpdateRoomRequest;
import com.HotelManagementSystem.entity.Room;
import com.HotelManagementSystem.repository.RoomRepository;
import com.HotelManagementSystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    @Override
    public Room createRoom(CreateRoomRequest request) {
        System.out.println("Creating room with isAvailable = " + request.isAvailable());
        Room room = Room.builder()
                .number(request.getNumber())
                .type(request.getType())
                .price(request.getPrice())
                .available(request.isAvailable()) // this works with Lombok
                .build();
        return roomRepository.save(room);
    }


    @Override
    public Room updateRoom(Long id, UpdateRoomRequest request) {
        System.out.println("Updating room with isAvailable = " + request.isAvailable());
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));

        room.setType(request.getType());
        room.setPrice(request.getPrice());
        room.setAvailable(request.isAvailable());

        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
