package com.gurjasproject.hotelapp.controller;

import com.gurjasproject.hotelapp.model.Room;
import com.gurjasproject.hotelapp.response.RoomResponse;
import com.gurjasproject.hotelapp.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


// controller method is responsible for both returning data in an appropriate format
// To achieve a better separation of concerns,
// we have business in its service layer
// This will not only simplify our code, but it will allow us to deploy and
// scale the controller and the business logic separately.
@RestController
//The @ResponseBody annotation tells a controller
// that the object returned is automatically serialized into JSON
// and passed back into the HttpResponse object
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final IRoomService roomService;
    //access modifier - private
    //final keyword is used to make the variable permenent or constant, it cannot be changed anymore
    //IRoomService is type

    //endpoint of the POST method is mapped to url: /add/new-room
    @PostMapping("/add/new-room")   //maps the POST Request to this method
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {
        Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(),
                savedRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/room/types")
    public List<String> getRoomTypes(){
        return roomService.getAllRoomTypes();
    }
}



