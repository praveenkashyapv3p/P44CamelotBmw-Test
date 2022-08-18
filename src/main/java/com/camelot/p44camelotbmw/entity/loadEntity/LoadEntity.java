package com.camelot.p44camelotbmw.entity.loadEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    
    public String getMasterShipmentId() {
        return masterShipmentId;
    }
    
    public void setMasterShipmentId(String masterShipmentId) {
        this.masterShipmentId = masterShipmentId;
    }
    
    public PickupStopReference getPickupStopReference() {
        return pickupStopReference;
    }
    
    public void setPickupStopReference(PickupStopReference pickupStopReference) {
        this.pickupStopReference = pickupStopReference;
    }
    
    public DeliveryStopReference getDeliveryStopReference() {
        return deliveryStopReference;
    }
    
    public void setDeliveryStopReference(DeliveryStopReference deliveryStopReference) {
        this.deliveryStopReference = deliveryStopReference;
    }
    
    public List<Identifier> getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LoadEntity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("masterShipmentId");
        sb.append('=');
        sb.append(((this.masterShipmentId == null) ? "<null>" : this.masterShipmentId));
        sb.append(',');
        sb.append("pickupStopReference");
        sb.append('=');
        sb.append(((this.pickupStopReference == null) ? "<null>" : this.pickupStopReference));
        sb.append(',');
        sb.append("deliveryStopReference");
        sb.append('=');
        sb.append(((this.deliveryStopReference == null) ? "<null>" : this.deliveryStopReference));
        sb.append(',');
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("items");
        sb.append('=');
        sb.append(((this.items == null) ? "<null>" : this.items));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}