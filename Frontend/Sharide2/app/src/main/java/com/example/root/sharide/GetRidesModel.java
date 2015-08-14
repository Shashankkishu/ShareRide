package com.example.root.sharide;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shashank on 14/8/15.
 */
public class GetRidesModel {

    @SerializedName("rides")
    private List<RidePost> rides;

    public List<RidePost> getRides() {
        return rides;
    }

    public void setRides(List<RidePost> rides) {
        this.rides = rides;
    }
}
