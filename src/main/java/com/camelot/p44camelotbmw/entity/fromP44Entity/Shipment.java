package com.camelot.p44camelotbmw.entity.fromP44Entity;

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
public class Shipment {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("shipmentShareLink")
    @Expose
    private String shipmentShareLink;
    @SerializedName("routeInfo")
    @Expose
    private RouteInfo routeInfo;
    @SerializedName("relatedShipments")
    @Expose
    private List<RelatedShipment> relatedShipments = null;
    
}