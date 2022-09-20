package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointOfDelivery {
    
    @SerializedName("podLoc")
    @Expose
    private String podLoc;
    @SerializedName("arrivalActual")
    @Expose
    private String arrivalActual;
    @SerializedName("arrivalPlanned")
    @Expose
    private String arrivalPlanned;
    @SerializedName("arrivalPrediction")
    @Expose
    private String arrivalPrediction;
    
    public String getPodLoc() {
        return podLoc;
    }
    
    public void setPodLoc(String podLoc) {
        this.podLoc = podLoc;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PointOfDelivery.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("podLoc");
        sb.append('=');
        sb.append(((this.podLoc == null) ? "<null>" : this.podLoc));
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
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}