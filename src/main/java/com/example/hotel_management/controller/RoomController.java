package com.example.hotel_management.controller;

import com.example.hotel_management.exception.RoomNotFoundException;
import com.example.hotel_management.model.Room;
import com.example.hotel_management.repository.RoomRepository;
import com.example.hotel_management.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//RESTful web controller,
//Combines @Controller and @ResponseBody so that all methods return JSON or XML data directly in the response body
@RestController
@RequestMapping("/rooms")

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }


    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> allRooms = roomService.getAllRooms();
        return ResponseEntity.ok(allRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id){
        Optional<Room> room = roomService.getRoomById(id);
        return room.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room){
        Room created = roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedRoom(@PathVariable Long id, @RequestBody Room room){
        try {
            Room updated = roomService.updateRoom(id, room);
            return ResponseEntity.ok(updated);
        } catch (RoomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id){
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } catch (RoomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
