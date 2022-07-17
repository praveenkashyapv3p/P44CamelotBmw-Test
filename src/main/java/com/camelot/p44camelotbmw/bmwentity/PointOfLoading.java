package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointOfLoading {

    @SerializedName("polLoc")
    @Expose
    private String polLoc;
    @SerializedName("departurePrediction")
    @Expose
    private String departurePrediction;
    @SerializedName("departureActual")
    @Expose
    private String departureActual;
    @SerializedName("arrivalPrediction")
    @Expose
    private String arrivalPrediction;
    @SerializedName("arrivalActual")
    @Expose
    private String arrivalActual;

    public String getPolLoc() {
        return polLoc;
    }

    public void setPolLoc(String polLoc) {
        this.polLoc = polLoc;
    }

    public String getDeparturePrediction() {
        return departurePrediction;
    }

    public void setDeparturePrediction(String departurePrediction) {
        this.departurePrediction = departurePrediction;
    }

    public String getDepartureActual() {
        return departureActual;
    }

    public void setDepartureActual(String departureActual) {
        this.departureActual = departureActual;
    }

    public String getArrivalPrediction() {
        return arrivalPrediction;
    }

    public void setArrivalPrediction(String arrivalPrediction) {
        this.arrivalPrediction = arrivalPrediction;
    }

    public String getArrivalActual() {
        return arrivalActual;
    }

    public void setArrivalActual(String arrivalActual) {
        this.arrivalActual = arrivalActual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PointOfLoading.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("polLoc");
        sb.append('=');
        sb.append(((this.polLoc == null) ? "<null>" : this.polLoc));
        sb.append(',');
        sb.append("departurePrediction");
        sb.append('=');
        sb.append(((this.departurePrediction == null) ? "<null>" : this.departurePrediction));
        sb.append(',');
        sb.append("departureActual");
        sb.append('=');
        sb.append(((this.departureActual == null) ? "<null>" : this.departureActual));
        sb.append(',');
        sb.append("arrivalPrediction");
        sb.append('=');
        sb.append(((this.arrivalPrediction == null) ? "<null>" : this.arrivalPrediction));
        sb.append(',');
        sb.append("arrivalActual");
        sb.append('=');
        sb.append(((this.arrivalActual == null) ? "<null>" : this.arrivalActual));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
