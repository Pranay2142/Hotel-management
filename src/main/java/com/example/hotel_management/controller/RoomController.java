package com.example.hotel_management.controller;

import com.example.hotel_management.model.Room;
import com.example.hotel_management.repository.RoomRepository;
import com.example.hotel_management.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }


    @PostMapping
    public Room createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public Room updatedRoom(@PathVariable Long id, @RequestBody Room room){
        return roomService.updateRoom(id,room);
    }

}
