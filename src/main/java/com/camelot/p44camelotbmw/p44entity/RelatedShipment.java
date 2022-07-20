package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelatedShipment {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_RelatedShipment> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("shipmentShareLink")
    @Expose
    private String shipmentShareLink;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public List<Identifier_RelatedShipment> getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(List<Identifier_RelatedShipment> identifiers) {
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
    
}