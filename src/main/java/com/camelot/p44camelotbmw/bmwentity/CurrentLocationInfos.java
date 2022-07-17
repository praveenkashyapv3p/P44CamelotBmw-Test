
package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentLocationInfos {

    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("geoDateTimeUTC")
    @Expose
    private String geoDateTimeUTC;
    @SerializedName("locationID")
    @Expose
    private String locationID;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("statusName")
    @Expose
    private String statusName;
    @SerializedName("timeStamps")
    @Expose
    private String timeStamps;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGeoDateTimeUTC() {
        return geoDateTimeUTC;
    }

    public void setGeoDateTimeUTC(String geoDateTimeUTC) {
        this.geoDateTimeUTC = geoDateTimeUTC;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(String timeStamps) {
        this.timeStamps = timeStamps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CurrentLocationInfos.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("longitude");
        sb.append('=');
        sb.append(((this.longitude == null) ? "<null>" : this.longitude));
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(((this.latitude == null) ? "<null>" : this.latitude));
        sb.append(',');
        sb.append("geoDateTimeUTC");
        sb.append('=');
        sb.append(((this.geoDateTimeUTC == null) ? "<null>" : this.geoDateTimeUTC));
        sb.append(',');
        sb.append("locationID");
        sb.append('=');
        sb.append(((this.locationID == null) ? "<null>" : this.locationID));
        sb.append(',');
        sb.append("locationName");
        sb.append('=');
        sb.append(((this.locationName == null) ? "<null>" : this.locationName));
        sb.append(',');
        sb.append("statusCode");
        sb.append('=');
        sb.append(((this.statusCode == null) ? "<null>" : this.statusCode));
        sb.append(',');
        sb.append("statusName");
        sb.append('=');
        sb.append(((this.statusName == null) ? "<null>" : this.statusName));
        sb.append(',');
        sb.append("timeStamps");
        sb.append('=');
        sb.append(((this.timeStamps == null) ? "<null>" : this.timeStamps));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
