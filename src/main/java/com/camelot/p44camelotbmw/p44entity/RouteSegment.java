
package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteSegment {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fromStopId")
    @Expose
    private String fromStopId;
    @SerializedName("toStopId")
    @Expose
    private String toStopId;
    @SerializedName("transportationMode")
    @Expose
    private String transportationMode;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_RouteSegment> identifiers = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public String getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(String transportationMode) {
        this.transportationMode = transportationMode;
    }

    public List<Identifier_RouteSegment> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier_RouteSegment> identifiers) {
        this.identifiers = identifiers;
    }

}
