package com.qacg.travelapp.presents;

import com.qacg.travelapp.models.Place;
import com.qacg.travelapp.models.Profile;
import com.qacg.travelapp.views.IPlacesView;

import java.util.ArrayList;
import java.util.List;

public class PlacesPresenter {

    private IPlacesView view;

    public PlacesPresenter(IPlacesView view) {
        this.view = view;
    }

    public void loadPlaces() {
        List<Place> tempPlaces = new ArrayList<>();
        Place tempPlace = new Place();
        Profile tempProfile = new Profile();

        tempProfile.setNameProfile("Scarlett Johansson");
        tempPlace.setProfile(tempProfile);
        tempPlace.setNamePlace("Playa del Carmen");
        tempPlace.setDate("hace 2 días");
        tempPlace.setTotalLikes(72);
        tempPlace.setDescription("Es una de las playas preferidas para vacacionar en la Riviera Maya, disfrutando su  fina y blanca arena bajo aguas verde-azules.");
        tempPlace.setTotalComments(14);
        tempPlaces.add(tempPlace);
        tempPlace=new Place();

        tempPlace.setProfile(tempProfile);
        tempPlace.setNamePlace("Boca de Río");
        tempPlace.setDate("hace 2 días");
        tempPlace.setTotalLikes(42);
        tempPlace.setDescription("Es una de las playas preferidas para vacacionar en Veracruz, disfrutando su  fina y blanca arena bajo aguas negras.");
        tempPlace.setTotalComments(34);
        tempPlaces.add(tempPlace);

        view.placesFound(tempPlaces);


    }
}
