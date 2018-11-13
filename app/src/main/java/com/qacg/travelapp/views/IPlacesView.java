package com.qacg.travelapp.views;

import com.qacg.travelapp.models.Place;

import java.util.List;

public interface IPlacesView {

    void placesFound(List<Place> places);
    void placesNotFound();
}
