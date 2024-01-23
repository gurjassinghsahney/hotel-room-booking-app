package com.gurjasproject.hotelapp.repository;

import com.gurjasproject.hotelapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//jparepository is just an interface with CRUD methods to make life easier
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("SELECT DISTINCT r.roomType FROM Room r") //room entity is represented by r
    // a select query to get room_type column values from database

    List<String> findDistinctRoomTypes();   //this is a method with a return type of List<String>
}
