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
public class P44ToBmw {
    
    @SerializedName("lifecycleStatus")
    @Expose
    private String lifecycleStatus;
    @SerializedName("lifecycleStatusVerbose")
    @Expose
    private String lifecycleStatusVerbose;
    @SerializedName("mainTransportMode")
    @Expose
    private String mainTransportMode;
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("leadTime")
    @Expose
    private String leadTime;
    @SerializedName("currentLeadTimePerLeg")
    @Expose
    private String currentLeadTimePerLeg;
    @SerializedName("currentLeadTimePickUpUntilCurrentTimestamp")
    @Expose
    private String currentLeadTimePickUpUntilCurrentTimestamp;
    @SerializedName("currentLeadTimePickUpUntilDelivery")
    @Expose
    private String currentLeadTimePickUpUntilDelivery;
    @SerializedName("currentLeadTimePickUpUntilEta")
    @Expose
    private String currentLeadTimePickUpUntilEta;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    @SerializedName("currentLocationInfo")
    @Expose
    private CurrentLocationInfo currentLocationInfo;
    @SerializedName("deliveryInformation")
    @Expose
    private DeliveryInformation deliveryInformation;
    @SerializedName("identifier")
    @Expose
    private Identifier identifier;
    @SerializedName("transportLegInfo")
    @Expose
    private TransportLegInfo transportLegInfo;
    @SerializedName("containerDimension")
    @Expose
    private ContainerDimension containerDimension;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;
    
}