package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteInfo {
    
    @SerializedName("stops")
    @Expose
    private List<Stop> stops = null;
    @SerializedName("routeSegments")
    @Expose
    private List<RouteSegment> routeSegments = null;
    
    public List<Stop> getStops() {
        return stops;
    }
    
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
    
    public List<RouteSegment> getRouteSegments() {
        return routeSegments;
    }
    
    public void setRouteSegments(List<RouteSegment> routeSegments) {
        this.routeSegments = routeSegments;
    }
    
}