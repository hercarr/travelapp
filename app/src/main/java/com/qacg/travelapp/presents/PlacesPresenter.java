package com.qacg.travelapp.presents;

import com.qacg.travelapp.api.ResourceGenerator;
import com.qacg.travelapp.models.Place;
import com.qacg.travelapp.utils.TravelApp;
import com.qacg.travelapp.views.IPlacesView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesPresenter {

    private IPlacesView view;
    private final Realm realm;


    public PlacesPresenter(IPlacesView view) {
        this.view = view;
        realm = Realm.getDefaultInstance();
    }

    public void loadPlaces() {
        if (TravelApp.isConnected()) {
            Call<List<Place>> call = ResourceGenerator.getTravelResource().getAllPlaces();

            call.enqueue(new Callback<List<Place>>() {
                @Override
                public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                    view.hideSwipe();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().size() > 0) {
                            view.placesFound(response.body());
                            saveToRealm(response.body());
                        } else {
                            view.placesNotFound();
                        }
                    } else {
                        view.connectionUnavailable();
                    }
                }

                @Override
                public void onFailure(Call<List<Place>> call, Throwable t) {
                    view.hideSwipe();
                    view.connectionUnavailable();
                    view.placesFound(getLocalPlaces());
                }
            });
        } else {
            view.hideSwipe();
            view.showNoInternetConnectionMsg();
            view.placesFound(getLocalPlaces());
        }
    }

    // Realm
    private void saveToRealm(final List<Place> places) {
        deleteLocalPlaces();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(places);
            }
        });
    }

    private List<Place> getLocalPlaces() {
        return realm.where(Place.class).findAll();
    }

    /**
     * Elimina el caché
     */
    private void deleteLocalPlaces() {
        final RealmResults<Place> places = realm.where(Place.class).findAll();
        if (places != null) {
            realm.executeTransaction(
                    new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            places.deleteAllFromRealm();
                        }
                    }
            );
        }
    }
}
