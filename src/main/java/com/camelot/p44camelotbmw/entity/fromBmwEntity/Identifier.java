package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identifier {
    
    @SerializedName("containerId")
    @Expose
    private String containerId;
    @SerializedName("billOfLading")
    @Expose
    private String billOfLading;
    @SerializedName("bmwShipmentId")
    @Expose
    private String bmwShipmentId;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    
    public String getContainerId() {
        return containerId;
    }
    
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
    
    public String getBillOfLading() {
        return billOfLading;
    }
    
    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }
    
    public String getBmwShipmentId() {
        return bmwShipmentId;
    }
    
    public void setBmwShipmentId(String bmwShipmentId) {
        this.bmwShipmentId = bmwShipmentId;
    }
    
    public String getBookingNumber() {
        return bookingNumber;
    }
    
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Identifier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("containerId");
        sb.append('=');
        sb.append(((this.containerId == null) ? "<null>" : this.containerId));
        sb.append(',');
        sb.append("billOfLading");
        sb.append('=');
        sb.append(((this.billOfLading == null) ? "<null>" : this.billOfLading));
        sb.append(',');
        sb.append("bmwShipmentId");
        sb.append('=');
        sb.append(((this.bmwShipmentId == null) ? "<null>" : this.bmwShipmentId));
        sb.append(',');
        sb.append("bookingNumber");
        sb.append('=');
        sb.append(((this.bookingNumber == null) ? "<null>" : this.bookingNumber));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}