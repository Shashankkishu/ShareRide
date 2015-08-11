package com.example.root.sharide;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shashank Bhusha on 7/12/2015.
 */
public class RidePost {

    @SerializedName("initiator")
    private String lInitiator;

    @SerializedName("origin")
    private String lOrigin;
    @SerializedName("destination")
    private String lDestination;
    @SerializedName("date")
    private String lDate;
    @SerializedName("time")
    private String lTime;
    @SerializedName("transport_mode")
    private String lTransport_mode;
    @SerializedName("price")
    private Integer lPrice;
    @SerializedName("has_booked")
    private Boolean lHas_booked;
    @SerializedName("only_girls")
    private Boolean lGirls;
    @SerializedName("only_boys")
    private Boolean lBoys;
    @SerializedName("latitude")
    private String lLatitude;

    public String getlInitiator() {
        return lInitiator;
    }

    public void setlInitiator(String lInitiator) {
        this.lInitiator = lInitiator;
    }

    public String getlOrigin() {
        return lOrigin;
    }

    public void setlOrigin(String lOrigin) {
        this.lOrigin = lOrigin;
    }

    public String getlDestination() {
        return lDestination;
    }

    public void setlDestination(String lDestination) {
        this.lDestination = lDestination;
    }

    public String getlDate() {
        return lDate;
    }

    public void setlDate(String lDate) {
        this.lDate = lDate;
    }

    public String getlTime() {
        return lTime;
    }

    public void setlTime(String lTime) {
        this.lTime = lTime;
    }

    public String getlTransport_mode() {
        return lTransport_mode;
    }

    public void setlTransport_mode(String lTransport_mode) {
        this.lTransport_mode = lTransport_mode;
    }

    public Integer getlPrice() {
        return lPrice;
    }

    public void setlPrice(Integer lPrice) {
        this.lPrice = lPrice;
    }

    public Boolean getlHas_bboked() {
        return lHas_booked;
    }

    public void setlHas_bboked(Boolean lHas_bboked) {
        this.lHas_booked = lHas_bboked;
    }

    public Boolean getlGirls() {
        return lGirls;
    }

    public void setlGirls(Boolean lGirls) {
        this.lGirls = lGirls;
    }

    public Boolean getlBoys() {
        return lBoys;
    }

    public void setlBoys(Boolean lBoys) {
        this.lBoys = lBoys;
    }

    public String getlLatitude() {
        return lLatitude;
    }

    public void setlLatitude(String lLatitude) {
        this.lLatitude = lLatitude;
    }

    public String getlLongitude() {
        return lLongitude;
    }

    public void setlLongitude(String lLongitude) {
        this.lLongitude = lLongitude;
    }

    public ArrayList<OtherRider> getlOther_Riders() {
        return lOther_Riders;
    }

    public void setlOther_Riders(ArrayList<OtherRider> lOther_Riders) {
        this.lOther_Riders = lOther_Riders;
    }

    @SerializedName("longitude")
    private String lLongitude;
    @SerializedName("other_riders")
    private ArrayList<OtherRider> lOther_Riders;

}
