package com.shows.booking.controller;

import com.shows.booking.model.Show;
import com.shows.booking.model.Booking;
import com.shows.booking.service.IBookingService;
import com.shows.booking.service.IShowService;
import com.shows.booking.service.ITrendingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final IShowService showService;

    private final IBookingService bookingService;

    private final ITrendingService trendingService;

    @Autowired
    public ShowController(IShowService showService, IBookingService bookingService, ITrendingService trendingService) {
        this.showService = showService;
        this.bookingService = bookingService;
        this.trendingService = trendingService;
    }

    @PostMapping("/register")
    public ResponseEntity<Show> registerShow(@RequestParam String name, @RequestParam String genre) {
        try {
            Show show = showService.registerShow(name, genre);
            return new ResponseEntity<>(show, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/slots")
    public ResponseEntity<Show> onboardShowSlots(@RequestParam String showName, @RequestBody Map<String, Integer> slotCapacities) {
        try {
            Show show = showService.onboardShowSlots(showName, slotCapacities);
            return new ResponseEntity<>(show, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Show>> getShowsByGenre(@RequestParam String genre) {
        try {
            List<Show> shows = showService.getShowsByGenre(genre);
            if (shows.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tickets/book")
    public ResponseEntity<Booking> bookTicket(
            @RequestParam String userName,
            @RequestParam String showName,
            @RequestParam String slot,
            @RequestParam int persons) {
        try {
            Booking booking = bookingService.bookTicket(userName, showName, slot, persons);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tickets/cancel")
    public ResponseEntity<String> cancelBooking(@RequestParam String bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return new ResponseEntity<>("Booking Canceled", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while canceling booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/bookings")
    public ResponseEntity<List<Booking>> getBookings(@RequestParam String userName) {
        try {
            List<Booking> bookings = bookingService.viewBookings(userName);
            if (bookings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trending")
    public ResponseEntity<Show> getTrendingShow() {
        try {
            Show trendingShow = trendingService.getTrendingShow();
            if (trendingShow == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trendingShow, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}