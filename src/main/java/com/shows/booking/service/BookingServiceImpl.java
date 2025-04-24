package com.shows.booking.service;

import com.shows.booking.model.Booking;
import com.shows.booking.model.Show;
import com.shows.booking.model.AppUser;
import com.shows.booking.repository.BookingRepository;
import com.shows.booking.repository.ShowRepository;
import com.shows.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements IBookingService {

    private final ShowRepository showRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(ShowRepository showRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking bookTicket(String userName, String showName, String slot, int persons) {
        Show show = showRepository.findByName(showName);
        if (show == null) throw new RuntimeException("Show not found!");

        AppUser user = userRepository.findByName(userName);
        if (user == null) {
            user = new AppUser();
            user.setName(userName);
            userRepository.save(user);
        }

        int capacity = show.getSlots().getOrDefault(slot, 0);
        if (capacity < persons) {
            show.getWaitlist().get(slot).add(userName);
            showRepository.save(show);
            throw new RuntimeException("Slot full! Added to waitlist.");
        }

        show.getSlots().put(slot, capacity - persons);
        showRepository.save(show);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSlot(slot);
        booking.setPersons(persons);
        user.getBookings().add(booking);
        userRepository.save(user);

        return booking;
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null); // Fetch the booking by its ID
        if (booking == null) {
            throw new RuntimeException("Booking not found!");
        }

        Show show = booking.getShow();
        String slot = booking.getSlot();
        int persons = booking.getPersons();

        Map<String, Integer> slots = show.getSlots();
        slots.put(slot, slots.get(slot) + persons);

        List<String> waitlist = show.getWaitlist().get(slot);
        if (!waitlist.isEmpty()) {
            String nextUser = waitlist.remove(0);
            bookTicket(nextUser, show.getName(), slot, persons);
        }

        bookingRepository.delete(booking);

        showRepository.save(show);

    }

    @Override
    public List<Booking> viewBookings(String userName) {
        AppUser user = userRepository.findByName(userName);
        if (user == null) throw new RuntimeException("User not found!");
        return user.getBookings();
    }
}