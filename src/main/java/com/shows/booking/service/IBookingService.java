package com.shows.booking.service;


import com.shows.booking.model.Booking;

import java.util.List;

public interface IBookingService {
    Booking bookTicket(String userName, String showName, String slot, int persons);

    void cancelBooking(String bookingId);

    List<Booking> viewBookings(String userName);
}