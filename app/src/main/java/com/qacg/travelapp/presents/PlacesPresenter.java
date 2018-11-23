package com.qacg.travelapp.presents;

import com.qacg.travelapp.api.ResourceGenerator;
import com.qacg.travelapp.models.Place;
import com.qacg.travelapp.views.IPlacesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesPresenter {

    private IPlacesView view;

    public PlacesPresenter(IPlacesView view) {
        this.view = view;
    }

    public void loadPlaces() {

        Call<List<Place>> call = ResourceGenerator.getTravelResource().getAllPlaces();

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().size() > 0) {
                        view.placesFound(response.body());
                    } else {
                        view.placesNotFound();
                    }
                } else {
                    view.connectionUnavailable();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                view.connectionUnavailable();
            }
        });
    }
}
