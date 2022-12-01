package com.camelot.p44camelotbmw.entity.loadEntity;

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
public class LoadEntity {
    
    @SerializedName("masterShipmentId")
    @Expose
    private String masterShipmentId;
    @SerializedName("pickupStopReference")
    @Expose
    private PickupStopReference pickupStopReference;
    @SerializedName("deliveryStopReference")
    @Expose
    private DeliveryStopReference deliveryStopReference;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier> identifiers = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    
}