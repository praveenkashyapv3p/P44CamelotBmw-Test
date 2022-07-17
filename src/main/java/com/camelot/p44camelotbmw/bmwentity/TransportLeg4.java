package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportLeg4 {

    @SerializedName("tsp4Loc")
    @Expose
    private String tsp4Loc;
    @SerializedName("arrivalPrediction")
    @Expose
    private String arrivalPrediction;
    @SerializedName("arrivalActual")
    @Expose
    private String arrivalActual;
    @SerializedName("departurePrediction")
    @Expose
    private String departurePrediction;
    @SerializedName("departureActual")
    @Expose
    private String departureActual;

    public String getTsp4Loc() {
        return tsp4Loc;
    }

    public void setTsp4Loc(String tsp4Loc) {
        this.tsp4Loc = tsp4Loc;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TransportLeg4.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("tspLoc");
        sb.append('=');
        sb.append(((this.tsp4Loc == null) ? "<null>" : this.tsp4Loc));
        sb.append(',');
        sb.append("arrivalPrediction");
        sb.append('=');
        sb.append(((this.arrivalPrediction == null) ? "<null>" : this.arrivalPrediction));
        sb.append(',');
        sb.append("arrivalActual");
        sb.append('=');
        sb.append(((this.arrivalActual == null) ? "<null>" : this.arrivalActual));
        sb.append(',');
        sb.append("departurePrediction");
        sb.append('=');
        sb.append(((this.departurePrediction == null) ? "<null>" : this.departurePrediction));
        sb.append(',');
        sb.append("departureActual");
        sb.append('=');
        sb.append(((this.departureActual == null) ? "<null>" : this.departureActual));
        sb.append(',');
        if (sb.charAt((sb.length() - 4)) == ',') {
            sb.setCharAt((sb.length() - 4), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
