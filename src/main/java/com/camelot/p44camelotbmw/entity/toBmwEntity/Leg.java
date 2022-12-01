package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Leg {
    
    @SerializedName("tspLoc")
    @Expose
    private String tspLoc;
    @SerializedName("legNumber")
    @Expose
    private String legNumber;
    @SerializedName("arrivalPrediction")
    @Expose
    private String arrivalPrediction;
    @SerializedName("arrivalPlanned")
    @Expose
    private String arrivalPlanned;
    @SerializedName("arrivalActual")
    @Expose
    private String arrivalActual;
    @SerializedName("departurePrediction")
    @Expose
    private String departurePrediction;
    @SerializedName("departurePlanned")
    @Expose
    private String departurePlanned;
    @SerializedName("departureActual")
    @Expose
    private String departureActual;
    
}