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
    
}