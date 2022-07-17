
package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public String getShipmentShareLink() {
        return shipmentShareLink;
    }

    public void setShipmentShareLink(String shipmentShareLink) {
        this.shipmentShareLink = shipmentShareLink;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }

    public List<RelatedShipment> getRelatedShipments() {
        return relatedShipments;
    }

    public void setRelatedShipments(List<RelatedShipment> relatedShipments) {
        this.relatedShipments = relatedShipments;
    }

}
