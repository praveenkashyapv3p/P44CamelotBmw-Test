package com.camelot.p44camelotbmw.entity.loadEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    
    @SerializedName("orderIdentifierReferences")
    @Expose
    private List<OrderIdentifierReference> orderIdentifierReferences = null;
    @SerializedName("unitQuantity")
    @Expose
    private String unitQuantity;
    @SerializedName("unitType")
    @Expose
    private String unitType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_items> identifiers = null;
    
    public List<OrderIdentifierReference> getOrderIdentifierReferences() {
        return orderIdentifierReferences;
    }
    
    public void setOrderIdentifierReferences(List<OrderIdentifierReference> orderIdentifierReferences) {
        this.orderIdentifierReferences = orderIdentifierReferences;
    }
    
    public String getUnitQuantity() {
        return unitQuantity;
    }
    
    public void setUnitQuantity(String unitQuantity) {
        this.unitQuantity = unitQuantity;
    }
    
    public String getUnitType() {
        return unitType;
    }
    
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Identifier_items> getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(List<Identifier_items> identifiers) {
        this.identifiers = identifiers;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderIdentifierReferences");
        sb.append('=');
        sb.append(((this.orderIdentifierReferences == null) ? "<null>" : this.orderIdentifierReferences));
        sb.append(',');
        sb.append("unitQuantity");
        sb.append('=');
        sb.append(((this.unitQuantity == null) ? "<null>" : this.unitQuantity));
        sb.append(',');
        sb.append("unitType");
        sb.append('=');
        sb.append(((this.unitType == null) ? "<null>" : this.unitType));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}