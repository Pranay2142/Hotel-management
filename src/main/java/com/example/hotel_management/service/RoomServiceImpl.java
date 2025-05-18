package com.example.hotel_management.service;

import com.example.hotel_management.exception.RoomNotFoundException;
import com.example.hotel_management.model.Room;
import com.example.hotel_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Long id) {
       return roomRepository.findById(id);
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) throws RoomNotFoundException {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));
        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setType(updatedRoom.getType());
        existingRoom.setAvailable(updatedRoom.isAvailable());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        if(!roomRepository.existsById(id)){
            throw new RoomNotFoundException("Room not found with id: "+ id);
        }
        roomRepository.deleteById(id);

    }
}
