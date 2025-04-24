package com.shows.booking.service;

import com.shows.booking.model.Show;
import com.shows.booking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowServiceImpl implements IShowService {


    private final ShowRepository showRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show registerShow(String name, String genre) {
        Show show = new Show();
        show.setName(name);
        show.setGenre(genre);
        showRepository.save(show);
        return show;
    }

    @Override
    public Show onboardShowSlots(String showName, Map<String, Integer> slotCapacities) {
        Show show = showRepository.findByName(showName);
        if (show == null) throw new RuntimeException("Show not found!");

        for (Map.Entry<String, Integer> entry : slotCapacities.entrySet()) {
            show.getSlots().put(entry.getKey(), entry.getValue());
            show.getWaitlist().put(entry.getKey(), new LinkedList<>());
        }
        showRepository.save(show);
        return show;
    }

    @Override
    public List<Show> getShowsByGenre(String genre) {
        Optional<List<Show>> list = showRepository.findByGenre(genre);
        return list.orElseGet(List::of);
    }
}