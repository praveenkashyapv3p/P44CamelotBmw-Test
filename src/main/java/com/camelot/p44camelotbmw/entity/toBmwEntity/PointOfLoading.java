package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointOfLoading {
    
    @SerializedName("polLoc")
    @Expose
    private String polLoc;
    @SerializedName("arrivalActual")
    @Expose
    private String arrivalActual;
    @SerializedName("arrivalPlanned")
    @Expose
    private String arrivalPlanned;
    @SerializedName("arrivalPrediction")
    @Expose
    private String arrivalPrediction;
    @SerializedName("departureActual")
    @Expose
    private String departureActual;
    @SerializedName("departurePlanned")
    @Expose
    private String departurePlanned;
    @SerializedName("departurePrediction")
    @Expose
    private String departurePrediction;
    
    public String getPolLoc() {
        return polLoc;
    }
    
    public void setPolLoc(String polLoc) {
        this.polLoc = polLoc;
    }
    
    public String getArrivalActual() {
        return arrivalActual;
    }
    
    public void setArrivalActual(String arrivalActual) {
        this.arrivalActual = arrivalActual;
    }
    
    public String getArrivalPlanned() {
        return arrivalPlanned;
    }
    
    public void setArrivalPlanned(String arrivalPlanned) {
        this.arrivalPlanned = arrivalPlanned;
    }
    
    public String getArrivalPrediction() {
        return arrivalPrediction;
    }
    
    public void setArrivalPrediction(String arrivalPrediction) {
        this.arrivalPrediction = arrivalPrediction;
    }
    
    public String getDepartureActual() {
        return departureActual;
    }
    
    public void setDepartureActual(String departureActual) {
        this.departureActual = departureActual;
    }
    
    public String getDeparturePlanned() {
        return departurePlanned;
    }
    
    public void setDeparturePlanned(String departurePlanned) {
        this.departurePlanned = departurePlanned;
    }
    
    public String getDeparturePrediction() {
        return departurePrediction;
    }
    
    public void setDeparturePrediction(String departurePrediction) {
        this.departurePrediction = departurePrediction;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PointOfLoading.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("polLoc");
        sb.append('=');
        sb.append(((this.polLoc == null) ? "<null>" : this.polLoc));
        sb.append(',');
        sb.append("arrivalActual");
        sb.append('=');
        sb.append(((this.arrivalActual == null) ? "<null>" : this.arrivalActual));
        sb.append(',');
        sb.append("arrivalPlanned");
        sb.append('=');
        sb.append(((this.arrivalPlanned == null) ? "<null>" : this.arrivalPlanned));
        sb.append(',');
        sb.append("arrivalPrediction");
        sb.append('=');
        sb.append(((this.arrivalPrediction == null) ? "<null>" : this.arrivalPrediction));
        sb.append(',');
        sb.append("departureActual");
        sb.append('=');
        sb.append(((this.departureActual == null) ? "<null>" : this.departureActual));
        sb.append(',');
        sb.append("departurePlanned");
        sb.append('=');
        sb.append(((this.departurePlanned == null) ? "<null>" : this.departurePlanned));
        sb.append(',');
        sb.append("departurePrediction");
        sb.append('=');
        sb.append(((this.departurePrediction == null) ? "<null>" : this.departurePrediction));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}