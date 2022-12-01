package com.camelot.p44camelotbmw.entity.fromBmwEntity;

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
public class BmwToP44 {
    
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    @SerializedName("deliveryInformation")
    @Expose
    private DeliveryInformation deliveryInformation;
    @SerializedName("identifier")
    @Expose
    private Identifier identifier;
    @SerializedName("containerDimension")
    @Expose
    private ContainerDimension containerDimension;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;
    
}