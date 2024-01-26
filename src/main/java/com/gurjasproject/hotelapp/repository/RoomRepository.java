package com.gurjasproject.hotelapp.repository;

import com.gurjasproject.hotelapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//jparepository is just an interface with CRUD methods to make life easier
public interface RoomRepository extends JpaRepository<Room, Long> {

    // a SELECT query to get roomType column values from database
    @Query("SELECT DISTINCT r.roomType FROM Room r") //room entity is represented by r
    List<String> findDistinctRoomTypes();   //this is a method suing the query with a return type of List<String>
}
