package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentLocationInfo {
    
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("statusName")
    @Expose
    private String statusName;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("geoDateTimeUtc")
    @Expose
    private String geoDateTimeUtc;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("sourceofPositionData")
    @Expose
    private String sourceofPositionData;
    
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
    
    public String getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public String getGeoDateTimeUtc() {
        return geoDateTimeUtc;
    }
    
    public void setGeoDateTimeUtc(String geoDateTimeUtc) {
        this.geoDateTimeUtc = geoDateTimeUtc;
    }
    
    public String getLatitude() {
        return latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    public String getLocationId() {
        return locationId;
    }
    
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getLongitude() {
        return longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    public String getSourceofPositionData() {
        return sourceofPositionData;
    }
    
    public void setSourceofPositionData(String sourceofPositionData) {
        this.sourceofPositionData = sourceofPositionData;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CurrentLocationInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("statusCode");
        sb.append('=');
        sb.append(((this.statusCode == null) ? "<null>" : this.statusCode));
        sb.append(',');
        sb.append("statusName");
        sb.append('=');
        sb.append(((this.statusName == null) ? "<null>" : this.statusName));
        sb.append(',');
        sb.append("timeStamp");
        sb.append('=');
        sb.append(((this.timeStamp == null) ? "<null>" : this.timeStamp));
        sb.append(',');
        sb.append("geoDateTimeUtc");
        sb.append('=');
        sb.append(((this.geoDateTimeUtc == null) ? "<null>" : this.geoDateTimeUtc));
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(((this.latitude == null) ? "<null>" : this.latitude));
        sb.append(',');
        sb.append("locationId");
        sb.append('=');
        sb.append(((this.locationId == null) ? "<null>" : this.locationId));
        sb.append(',');
        sb.append("locationName");
        sb.append('=');
        sb.append(((this.locationName == null) ? "<null>" : this.locationName));
        sb.append(',');
        sb.append("longitude");
        sb.append('=');
        sb.append(((this.longitude == null) ? "<null>" : this.longitude));
        sb.append(',');
        sb.append("sourceofPositionData");
        sb.append('=');
        sb.append(((this.sourceofPositionData == null) ? "<null>" : this.sourceofPositionData));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}