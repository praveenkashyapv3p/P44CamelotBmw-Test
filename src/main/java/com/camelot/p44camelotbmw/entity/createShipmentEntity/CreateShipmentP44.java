package com.camelot.p44camelotbmw.entity.createShipmentEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateShipmentP44 {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;
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
    
    public List<Attribute> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    
    public List<RelatedShipment> getRelatedShipments() {
        return relatedShipments;
    }
    
    public void setRelatedShipments(List<RelatedShipment> relatedShipments) {
        this.relatedShipments = relatedShipments;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CreateShipmentP44.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null) ? "<null>" : this.attributes));
        sb.append(',');
        sb.append("relatedShipments");
        sb.append('=');
        sb.append(((this.relatedShipments == null) ? "<null>" : this.relatedShipments));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}