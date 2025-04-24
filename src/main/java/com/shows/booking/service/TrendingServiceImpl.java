package com.shows.booking.service;

import com.shows.booking.model.Show;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrendingServiceImpl implements ITrendingService {

    private Map<Show, Integer> trendingTracker = new HashMap<>();

    @Override
    public void updateTrending(Show show) {
        trendingTracker.put(show, trendingTracker.getOrDefault(show, 0) + 1);
    }

    @Override
    public Show getTrendingShow() {
        return trendingTracker.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}