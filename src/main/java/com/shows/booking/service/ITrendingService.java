package com.shows.booking.service;

import com.shows.booking.model.Show;

public interface ITrendingService {
    void updateTrending(Show show);

    Show getTrendingShow();
}