package com.shows.booking.service;


import com.shows.booking.model.Show;

import java.util.List;
import java.util.Map;

public interface IShowService {
    Show registerShow(String name, String genre);

    Show onboardShowSlots(String showName, Map<String, Integer> slotCapacities);

    List<Show> getShowsByGenre(String genre);
}