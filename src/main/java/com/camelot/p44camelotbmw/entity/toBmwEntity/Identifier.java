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
public class Identifier {
    
    @SerializedName("containerId")
    @Expose
    private String containerId;
    @SerializedName("p44InternalContainerId")
    @Expose
    private String p44InternalContainerId;
    @SerializedName("p44TechnicalCorrelationId")
    @Expose
    private String p44TechnicalCorrelationId;
    @SerializedName("billOfLading")
    @Expose
    private String billOfLading;
    @SerializedName("bmwBusinessRelation")
    @Expose
    private String bmwBusinessRelation;
    @SerializedName("bmwShipmentId")
    @Expose
    private String bmwShipmentId;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("vesselName")
    @Expose
    private String vesselName;
    
}