package com.example.hotel_management.service;

import com.example.hotel_management.exception.RoomNotFoundException;
import com.example.hotel_management.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    /**
     * Fetch all rooms.
     * @return list of all rooms.
     */
    List<Room> getAllRooms();

    /**
     * Get a room by its ID.
     * @param id the room ID.
     * @return the Room object if found, else Optional.empty().
     */
    Optional<Room> getRoomById(Long id);

    /**
     * Create a new room.
     * @param room Room to create.
     * @return the created Room.
     */
    Room createRoom(Room room);

    /**
     * Update an existing room.
     * @param id the ID of the room to update.
     * @param room updated room details.
     * @return updated Room.
     * @throws RoomNotFoundException if room with id does not exist.
     */
    Room updateRoom(Long id, Room room) throws RoomNotFoundException;

    /**
     * Delete a room by ID.
     * @param id the room ID.
     * @throws RoomNotFoundException if room with id does not exist.
     */
    void deleteRoom(Long id) throws RoomNotFoundException;

}
