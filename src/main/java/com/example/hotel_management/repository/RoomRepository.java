package com.example.hotel_management.repository;

import com.example.hotel_management.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Repository is a Spring annotation that marks this interface as a Repository component, allowing Spring to:
//Recognize it during component scanning
//Translate database exceptions into Spring’s DataAccessException

//JpaRepository eliminates the need to write boilerplate DAO code.


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByAvailable(boolean available);
    Room findByRoomNumber(String roomNumber);

}
