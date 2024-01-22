package com.gurjasproject.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HotelappApplication {

	public static void main(String[] args) {
		//getclass() is a method of the Object class
		// .class is used when there is no instance
		SpringApplication.run(HotelappApplication.class, args);

		//context meaning,
		//context is what changes state, for example a phone power button changes state of the phone
		//from switch OFF to switch ON.

	}

}
