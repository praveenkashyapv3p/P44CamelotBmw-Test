
package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("routeSegmentId")
    @Expose
    private String routeSegmentId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getRouteSegmentId() {
        return routeSegmentId;
    }

    public void setRouteSegmentId(String routeSegmentId) {
        this.routeSegmentId = routeSegmentId;
    }

}
