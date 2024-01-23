package com.gurjasproject.hotelapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Room {
    @Id     //The @Id annotation is inherited from jakarta.persistence.Id,
    //indicating the member field below is the primary key of current entity.
    @GeneratedValue(strategy = GenerationType.AUTO)    //auto_increment from mysql db
    private Long id;    //our primary key


    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked = false;
    @Lob
    private Blob photo;     //One method is to store the images as binary data,
        // also known as BLOB (Binary Large Object) data.
    @OneToMany(mappedBy = "room" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    //to keep track of booked rooms
    private List<BookedRoom> bookings;


    public Room() {
        //initializing the BookedRoom arraylist to empty when creating the object.
        this.bookings = new ArrayList<>();      //arraylist implements list interface, An
        // @Override tag is not required for implementing an interface, as there is nothing in the original interface methods to be overridden.
    }

    public void addBooking(BookedRoom booking) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked= true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);
    }
}
