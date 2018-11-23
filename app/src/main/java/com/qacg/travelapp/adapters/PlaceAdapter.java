package com.qacg.travelapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qacg.travelapp.R;
import com.qacg.travelapp.models.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private List<Place> places;
    private Context mContext;

    public PlaceAdapter(Context context) {
        mContext = context;
    }

    public void addList(List<Place> places) {
        if (this.places != null)
            this.places.addAll(places);
        else if (places != null && !places.isEmpty())
            this.places = places;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlaceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.dataBinding(places.get(i));
    }

    @Override
    public int getItemCount() {
        return places != null ? places.size() : 0;
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageProfile;
        private TextView nameProfile;
        private TextView date;
        private TextView totalLikes;
        private TextView namePlace;
        private ImageView imagePlace;
        private TextView description;
        private TextView numberComments;


        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            nameProfile = itemView.findViewById(R.id.nameProfile);
            date = itemView.findViewById(R.id.textDate);
            totalLikes = itemView.findViewById(R.id.totalLikes);
            imagePlace = itemView.findViewById(R.id.imagePlace);
            namePlace = itemView.findViewById(R.id.textTitlePlace);
            description = itemView.findViewById(R.id.textDescription);
            numberComments = itemView.findViewById(R.id.textNumberComments);
        }

        void dataBinding(Place place) {
            Glide.with(mContext)
                    .load(place.getProfile().getUrlImageProfile())
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageProfile);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(45));
            Glide.with(mContext)
                    .load(place.getUrlImagePlace())
                    .apply(requestOptions)
                    .into(imagePlace);
            nameProfile.setText(place.getProfile().getNameProfile());
            date.setText(place.getDate());
            totalLikes.setText(String.valueOf(place.getTotalLikes()));
            namePlace.setText(place.getNamePlace());
            description.setText(place.getDescription());
            numberComments.setText(String.valueOf(place.getTotalComments()));
        }
    }
}
