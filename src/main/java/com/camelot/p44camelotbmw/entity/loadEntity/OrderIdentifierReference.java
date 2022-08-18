package com.camelot.p44camelotbmw.entity.loadEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderIdentifierReference {
    
    @SerializedName("orderType")
    @Expose
    private String orderType;
    @SerializedName("orderIdentifier")
    @Expose
    private String orderIdentifier;
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    public String getOrderIdentifier() {
        return orderIdentifier;
    }
    
    public void setOrderIdentifier(String orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderIdentifierReference.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null) ? "<null>" : this.orderType));
        sb.append(',');
        sb.append("orderIdentifier");
        sb.append('=');
        sb.append(((this.orderIdentifier == null) ? "<null>" : this.orderIdentifier));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}