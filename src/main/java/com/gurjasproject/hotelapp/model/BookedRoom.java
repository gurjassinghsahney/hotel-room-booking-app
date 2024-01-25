package com.gurjasproject.hotelapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
//code first approach
@Entity // to create tables in mysql
@Getter
@Setter
@AllArgsConstructor
//JPA/Hibernate uses the default constructor method to create a bean class using reflections.
//thats why when we create a prameterised constructor we also need to create a noargsconstructor
@NoArgsConstructor
public class BookedRoom {
    @Id //from jakarta to create a primary key for our db table
    @GeneratedValue(strategy = GenerationType.AUTO) //using identity to generate Id column values
    private Long bookingId;

    @Column(name = "check_In")     // renaming columns not necessary
    private LocalDate checkInDate;

    @Column(name = "check_Out")
    private LocalDate checkOutDate;

    @Column(name = "GUest_FullName")

    private String guestFullName;
    @Column(name = "Guest_Email")
    private String guestEmail;

    @Column(name = "adults")
    private int NumOfAdults;
    @Column(name = "children")
    private int NumOfChildren;

    @Column(name = "total_guest")
    private int totalNumOfGuest;
    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    public void calculateToTotalNumberOfGuest(){
        this.totalNumOfGuest=this.NumOfAdults+NumOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateToTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateToTotalNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }


}
