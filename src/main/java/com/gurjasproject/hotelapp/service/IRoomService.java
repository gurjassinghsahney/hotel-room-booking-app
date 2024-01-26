package com.gurjasproject.hotelapp.service;

import com.gurjasproject.hotelapp.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

//The reason we use interfaces is for single responsibility principle
public interface IRoomService {

    Room addNewRoom(                    //method to add new room
            MultipartFile photo,
            String roomType,
            BigDecimal roomPrice) throws IOException, SQLException;

    List<String> getAllRoomTypes();     //method to get all room types added till now

}
