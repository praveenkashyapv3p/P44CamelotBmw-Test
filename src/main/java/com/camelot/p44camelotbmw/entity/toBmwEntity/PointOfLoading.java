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
    
}