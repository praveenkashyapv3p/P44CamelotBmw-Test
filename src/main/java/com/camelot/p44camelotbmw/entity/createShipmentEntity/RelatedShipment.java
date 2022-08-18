package com.camelot.p44camelotbmw.entity.createShipmentEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelatedShipment {
    
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_relatedShipment> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute_relatedShipments> attributes = null;
    
    public List<Identifier_relatedShipment> getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(List<Identifier_relatedShipment> identifiers) {
        this.identifiers = identifiers;
    }
    
    public List<Attribute_relatedShipments> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(List<Attribute_relatedShipments> attributes) {
        this.attributes = attributes;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RelatedShipment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null) ? "<null>" : this.attributes));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}