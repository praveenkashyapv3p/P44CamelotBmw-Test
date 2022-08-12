package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Material {
    
    @SerializedName("materialNumber")
    @Expose
    private String materialNumber;
    @SerializedName("purchaseOrder")
    @Expose
    private String purchaseOrder;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("deliverNoteNumber")
    @Expose
    private String deliverNoteNumber;
    
    public String getMaterialNumber() {
        return materialNumber;
    }
    
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }
    
    public String getPurchaseOrder() {
        return purchaseOrder;
    }
    
    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    
    public String getQuantity() {
        return quantity;
    }
    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getDeliverNoteNumber() {
        return deliverNoteNumber;
    }
    
    public void setDeliverNoteNumber(String deliverNoteNumber) {
        this.deliverNoteNumber = deliverNoteNumber;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Material.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("materialNumber");
        sb.append('=');
        sb.append(((this.materialNumber == null) ? "<null>" : this.materialNumber));
        sb.append(',');
        sb.append("purchaseOrder");
        sb.append('=');
        sb.append(((this.purchaseOrder == null) ? "<null>" : this.purchaseOrder));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null) ? "<null>" : this.quantity));
        sb.append(',');
        sb.append("deliverNoteNumber");
        sb.append('=');
        sb.append(((this.deliverNoteNumber == null) ? "<null>" : this.deliverNoteNumber));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}