package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material {
    
    @SerializedName("materialNumber")
    @Expose
    private String materialNumber;
    @SerializedName("purchaseOrder")
    @Expose
    private String purchaseOrder;
    @SerializedName("deliveryNoteNumber")
    @Expose
    private String deliveryNoteNumber;
    @SerializedName("p44Quantity")
    @Expose
    private String p44Quantity;
    @SerializedName("bmwQuantity")
    @Expose
    private String bmwQuantity;
    @SerializedName("quantityUnit")
    @Expose
    private String quantityUnit;
    
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
    
    public String getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }
    
    public void setDeliveryNoteNumber(String deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }
    
    public String getP44Quantity() {
        return p44Quantity;
    }
    
    public void setP44Quantity(String p44Quantity) {
        this.p44Quantity = p44Quantity;
    }
    
    public String getBmwQuantity() {
        return bmwQuantity;
    }
    
    public void setBmwQuantity(String bmwQuantity) {
        this.bmwQuantity = bmwQuantity;
    }
    
    public String getQuantityUnit() {
        return quantityUnit;
    }
    
    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
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
        sb.append("deliveryNoteNumber");
        sb.append('=');
        sb.append(((this.deliveryNoteNumber == null) ? "<null>" : this.deliveryNoteNumber));
        sb.append(',');
        sb.append("p44Quantity");
        sb.append('=');
        sb.append(((this.p44Quantity == null) ? "<null>" : this.p44Quantity));
        sb.append(',');
        sb.append("bmwQuantity");
        sb.append('=');
        sb.append(((this.bmwQuantity == null) ? "<null>" : this.bmwQuantity));
        sb.append(',');
        sb.append("quantityUnit");
        sb.append('=');
        sb.append(((this.quantityUnit == null) ? "<null>" : this.quantityUnit));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}