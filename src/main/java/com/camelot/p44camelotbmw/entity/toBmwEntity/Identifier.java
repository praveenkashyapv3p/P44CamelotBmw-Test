package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identifier {
    
    @SerializedName("containerId")
    @Expose
    private String containerId;
    @SerializedName("p44InternalContainerId")
    @Expose
    private String p44InternalContainerId;
    @SerializedName("p44TechnicalCorrelationId")
    @Expose
    private String p44TechnicalCorrelationId;
    @SerializedName("billOfLading")
    @Expose
    private String billOfLading;
    @SerializedName("bmwBusinessRelation")
    @Expose
    private String bmwBusinessRelation;
    @SerializedName("bmwShipmentId")
    @Expose
    private String bmwShipmentId;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("vesselName")
    @Expose
    private String vesselName;
    
    public String getContainerId() {
        return containerId;
    }
    
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
    
    public String getP44InternalContainerId() {
        return p44InternalContainerId;
    }
    
    public void setP44InternalContainerId(String p44InternalContainerId) {
        this.p44InternalContainerId = p44InternalContainerId;
    }
    
    public String getP44TechnicalCorrelationId() {
        return p44TechnicalCorrelationId;
    }
    
    public void setP44TechnicalCorrelationId(String p44TechnicalCorrelationId) {
        this.p44TechnicalCorrelationId = p44TechnicalCorrelationId;
    }
    
    public String getBillOfLading() {
        return billOfLading;
    }
    
    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }
    
    public String getBmwBusinessRelation() {
        return bmwBusinessRelation;
    }
    
    public void setBmwBusinessRelation(String bmwBusinessRelation) {
        this.bmwBusinessRelation = bmwBusinessRelation;
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
    
    public String getVesselName() {
        return vesselName;
    }
    
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Identifier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("containerId");
        sb.append('=');
        sb.append(((this.containerId == null) ? "<null>" : this.containerId));
        sb.append(',');
        sb.append("p44InternalContainerId");
        sb.append('=');
        sb.append(((this.p44InternalContainerId == null) ? "<null>" : this.p44InternalContainerId));
        sb.append(',');
        sb.append("p44TechnicalCorrelationId");
        sb.append('=');
        sb.append(((this.p44TechnicalCorrelationId == null) ? "<null>" : this.p44TechnicalCorrelationId));
        sb.append(',');
        sb.append("billOfLading");
        sb.append('=');
        sb.append(((this.billOfLading == null) ? "<null>" : this.billOfLading));
        sb.append(',');
        sb.append("bmwBusinessRelation");
        sb.append('=');
        sb.append(((this.bmwBusinessRelation == null) ? "<null>" : this.bmwBusinessRelation));
        sb.append(',');
        sb.append("bmwShipmentId");
        sb.append('=');
        sb.append(((this.bmwShipmentId == null) ? "<null>" : this.bmwShipmentId));
        sb.append(',');
        sb.append("bookingNumber");
        sb.append('=');
        sb.append(((this.bookingNumber == null) ? "<null>" : this.bookingNumber));
        sb.append(',');
        sb.append("vesselName");
        sb.append('=');
        sb.append(((this.vesselName == null) ? "<null>" : this.vesselName));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}