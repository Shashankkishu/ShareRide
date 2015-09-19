package com.example.root.sharide;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shashank Bhusha on 7/12/2015.
 */
public class RidePost {

    @SerializedName("ride-ID")
    private String lID;
    @SerializedName("admin_name")
    private String ladminname;

    public String getLadminemail() {
        return ladminemail;
    }

    public void setLadminemail(String ladminemail) {
        this.ladminemail = ladminemail;
    }

    public String getlID() {
        return lID;
    }

    public void setlID(String lID) {
        this.lID = lID;
    }

    public String getLadminname() {
        return ladminname;
    }

    public void setLadminname(String ladminname) {
        this.ladminname = ladminname;
    }

    public long getLmillis() {
        return lmillis;
    }

    public void setLmillis(long lmillis) {
        this.lmillis = lmillis;
    }

    public Integer getLfreeSpace() {
        return lfreeSpace;
    }

    public void setLfreeSpace(Integer lfreeSpace) {
        this.lfreeSpace = lfreeSpace;
    }

    public Boolean getlHas_booked() {
        return lHas_booked;
    }

    public ArrayList getRiders() {
        return riders;
    }

    public void setRiders(ArrayList riders) {
        this.riders = riders;
    }

    @SerializedName("admin_email")
    private String ladminemail;
    @SerializedName("origin")
    private String lOrigin;
    @SerializedName("millis")
    private long lmillis;
    @SerializedName("destination")
    private String lDestination;
    @SerializedName("date")
    private String lDate;
    @SerializedName("time")
    private String lTime;
    @SerializedName("freeSpace")
    private Integer lfreeSpace;
    @SerializedName("transport_mode")
    private String lTransport_mode;
    @SerializedName("transport_mode_info")
    private String lTransport_mode_info;
    @SerializedName("price")
    private Integer lPrice;
    @SerializedName("has_booked")
    private Boolean lHas_booked;
    @SerializedName("only_girls")
    private Boolean lGirls;
    @SerializedName("riders")
    private ArrayList riders;
//    @SerializedName("only_boys")
//    private Boolean lBoys;
    @SerializedName("latitude")
    private String lLatitude;
//    public String getID;

    public ArrayList getrides() {return riders;}

    public void setRiders(String lID) {this.riders = riders;}

    public String getID() {return lID;}

    public void setID(String lID) {this.lID = lID;}

    public String getlOrigin() {return lOrigin;}

    public void setlOrigin(String lOrigin) {this.lOrigin = lOrigin;}

    public String getlAdmin_name() {
        return ladminname;
    }

    public void setladminname(String ladminname) {
        this.ladminname = ladminname;
    }

    public long getlmillis() {
        return lmillis;
    }

    public void setlmillis(long lmillis) {
        this.lmillis = lmillis;
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

    public Integer getlfreeSpace() {
        return lfreeSpace;
    }

    public void setlfreeSpace(int lfreeSpace) {
        this.lfreeSpace = lfreeSpace;
    }

    public String getlTransport_mode() {
        return lTransport_mode;
    }

    public void setlTransport_mode(String lTransport_mode) {
        this.lTransport_mode = lTransport_mode;
    }
    public String getlTransport_mode_info() {
        return lTransport_mode_info;
    }

    public void setlTransport_mode_info(String lTransport_mode_info) {
        this.lTransport_mode_info = lTransport_mode_info;
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

    public void setlHas_booked(Boolean lHas_bboked) {
        this.lHas_booked = lHas_bboked;
    }

    public Boolean getlGirls() {
        return lGirls;
    }

    public void setlGirls(Boolean lGirls) {
        this.lGirls = lGirls;
    }

//    public Boolean getlBoys() {
//        return lBoys;
//    }
//
//    public void setlBoys(Boolean lBoys) {
//        this.lBoys = lBoys;
//    }

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
