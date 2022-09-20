package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryInformation {
    
    @SerializedName("planDeliveryDate")
    @Expose
    private String planDeliveryDate;
    @SerializedName("planPickUpDate")
    @Expose
    private String planPickUpDate;
    
    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }
    
    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }
    
    public String getPlanPickUpDate() {
        return planPickUpDate;
    }
    
    public void setPlanPickUpDate(String planPickUpDate) {
        this.planPickUpDate = planPickUpDate;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DeliveryInformation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("planDeliveryDate");
        sb.append('=');
        sb.append(((this.planDeliveryDate == null) ? "<null>" : this.planDeliveryDate));
        sb.append(',');
        sb.append("planPickUpDate");
        sb.append('=');
        sb.append(((this.planPickUpDate == null) ? "<null>" : this.planPickUpDate));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}