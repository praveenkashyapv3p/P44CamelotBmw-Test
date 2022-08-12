package com.camelot.p44camelotbmw.entity.fromP44Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
    
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("plannedDateTime")
    @Expose
    private String plannedDateTime;
    @SerializedName("routeSegmentId")
    @Expose
    private String routeSegmentId;
    @SerializedName("estimateDateTime")
    @Expose
    private String estimateDateTime;
    @SerializedName("estimateLastCalculatedDateTime")
    @Expose
    private String estimateLastCalculatedDateTime;
    
    public String getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getReceivedDateTime() {
        return receivedDateTime;
    }
    
    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStopId() {
        return stopId;
    }
    
    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
    
    public String getPlannedDateTime() {
        return plannedDateTime;
    }
    
    public void setPlannedDateTime(String plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }
    
    public String getRouteSegmentId() {
        return routeSegmentId;
    }
    
    public void setRouteSegmentId(String routeSegmentId) {
        this.routeSegmentId = routeSegmentId;
    }
    
    public String getEstimateDateTime() {
        return estimateDateTime;
    }
    
    public void setEstimateDateTime(String estimateDateTime) {
        this.estimateDateTime = estimateDateTime;
    }
    
    public String getEstimateLastCalculatedDateTime() {
        return estimateLastCalculatedDateTime;
    }
    
    public void setEstimateLastCalculatedDateTime(String estimateLastCalculatedDateTime) {
        this.estimateLastCalculatedDateTime = estimateLastCalculatedDateTime;
    }
    
}