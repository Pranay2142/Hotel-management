package com.example.hotel_management.controller;

import com.example.hotel_management.model.Room;
import com.example.hotel_management.repository.RoomRepository;
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
    //Injects the dependency of RoomRepository automatically at runtime using Spring's Dependency Injection (DI).
//@Autowired
//private RoomRepository roomRepository;

    //Preferred: Constructor Injection,  Promotes immutability
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    //Handles HTTP GET requests to /rooms.
    @GetMapping
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    // Handles HTTP POST requests to /rooms.
    /*@PostMapping
    //@RequestBody tells Spring to deserialize the incoming JSON payload into a Java object
    public Room createRoom(@RequestBody Room room){ //
        return roomRepository.save(room);
    }*/

    @PostMapping
    public ResponseEntity<?> createRoom(@Valid @RequestBody Room room, BindingResult result) {
        //BindingResult is an interface that holds the result of a validation and binding operation for a request.
        //BindingResult must come immediately after the @Valid annotated parameter.
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(roomRepository.save(room));
    }

}
