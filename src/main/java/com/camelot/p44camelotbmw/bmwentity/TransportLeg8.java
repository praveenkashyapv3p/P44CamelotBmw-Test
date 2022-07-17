package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportLeg8 {

    @SerializedName("tsp8Loc")
    @Expose
    private String tsp8Loc;
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

    public String getTsp8Loc() {
        return tsp8Loc;
    }

    public void setTsp8Loc(String tsp8Loc) {
        this.tsp8Loc = tsp8Loc;
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
        sb.append(TransportLeg8.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("tspLoc");
        sb.append('=');
        sb.append(((this.tsp8Loc == null) ? "<null>" : this.tsp8Loc));
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
        if (sb.charAt((sb.length() - 8)) == ',') {
            sb.setCharAt((sb.length() - 8), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
