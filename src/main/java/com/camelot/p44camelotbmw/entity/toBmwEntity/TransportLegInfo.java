package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    
}