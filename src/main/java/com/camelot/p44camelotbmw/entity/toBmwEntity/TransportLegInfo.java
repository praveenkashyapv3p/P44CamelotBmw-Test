package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransportLegInfo {
    
    @SerializedName("currentTransportMode")
    @Expose
    private String currentTransportMode;
    @SerializedName("transportSection")
    @Expose
    private String transportSection;
    @SerializedName("pointOfDelivery")
    @Expose
    private PointOfDelivery pointOfDelivery;
    @SerializedName("pointOfLoading")
    @Expose
    private PointOfLoading pointOfLoading;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = null;
    
    public String getCurrentTransportMode() {
        return currentTransportMode;
    }
    
    public void setCurrentTransportMode(String currentTransportMode) {
        this.currentTransportMode = currentTransportMode;
    }
    
    public String getTransportSection() {
        return transportSection;
    }
    
    public void setTransportSection(String transportSection) {
        this.transportSection = transportSection;
    }
    
    public PointOfDelivery getPointOfDelivery() {
        return pointOfDelivery;
    }
    
    public void setPointOfDelivery(PointOfDelivery pointOfDelivery) {
        this.pointOfDelivery = pointOfDelivery;
    }
    
    public PointOfLoading getPointOfLoading() {
        return pointOfLoading;
    }
    
    public void setPointOfLoading(PointOfLoading pointOfLoading) {
        this.pointOfLoading = pointOfLoading;
    }
    
    public List<Leg> getLegs() {
        return legs;
    }
    
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TransportLegInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentTransportMode");
        sb.append('=');
        sb.append(((this.currentTransportMode == null) ? "<null>" : this.currentTransportMode));
        sb.append(',');
        sb.append("transportSection");
        sb.append('=');
        sb.append(((this.transportSection == null) ? "<null>" : this.transportSection));
        sb.append(',');
        sb.append("pointOfDelivery");
        sb.append('=');
        sb.append(((this.pointOfDelivery == null) ? "<null>" : this.pointOfDelivery));
        sb.append(',');
        sb.append("pointOfLoading");
        sb.append('=');
        sb.append(((this.pointOfLoading == null) ? "<null>" : this.pointOfLoading));
        sb.append(',');
        sb.append("legs");
        sb.append('=');
        sb.append(((this.legs == null) ? "<null>" : this.legs));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}