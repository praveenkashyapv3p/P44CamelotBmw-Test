package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Identifiers {
    
    @SerializedName("p44InternalContainerId")
    @Expose
    private String internalP44Identifier;
    @SerializedName("containerId")
    @Expose
    private String containerID;
    @SerializedName("bmwShipmentId")
    @Expose
    private String bmwShipmentID;
    @SerializedName("bookingNumberBol")
    @Expose
    private String bookingNumberBOL;
    @SerializedName("vesselName")
    @Expose
    private String vesselName;
    @SerializedName("bmwBusinessUnit")
    @Expose
    private String bmwBusinessUnit;
    @SerializedName("correlationId")
    @Expose
    private String correlationId;
    
    public String getInternalP44Identifier() {
        return internalP44Identifier;
    }
    
    public void setInternalP44Identifier(String internalP44Identifier) {
        this.internalP44Identifier = internalP44Identifier;
    }
    
    public String getContainerID() {
        return containerID;
    }
    
    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }
    
    public String getBmwShipmentID() {
        return bmwShipmentID;
    }
    
    public void setBmwShipmentID(String bmwShipmentID) {
        this.bmwShipmentID = bmwShipmentID;
    }
    
    public String getBookingNumberBOL() {
        return bookingNumberBOL;
    }
    
    public void setBookingNumberBOL(String bookingNumberBOL) {
        this.bookingNumberBOL = bookingNumberBOL;
    }
    
    public String getVesselName() {
        return vesselName;
    }
    
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
    
    public String getBmwBusinessUnit() {
        return bmwBusinessUnit;
    }
    
    public void setBmwBusinessUnit(String bmwBusinessUnit) {
        this.bmwBusinessUnit = bmwBusinessUnit;
    }
    
    public String getCorrelationId() {
        return correlationId;
    }
    
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Identifiers.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("internalP44Identifier");
        sb.append('=');
        sb.append(((this.internalP44Identifier == null) ? "<null>" : this.internalP44Identifier));
        sb.append(',');
        sb.append("containerID");
        sb.append('=');
        sb.append(((this.containerID == null) ? "<null>" : this.containerID));
        sb.append(',');
        sb.append("bmwShipmentID");
        sb.append('=');
        sb.append(((this.bmwShipmentID == null) ? "<null>" : this.bmwShipmentID));
        sb.append(',');
        sb.append("bookingNumberBOL");
        sb.append('=');
        sb.append(((this.bookingNumberBOL == null) ? "<null>" : this.bookingNumberBOL));
        sb.append(',');
        sb.append("vesselName");
        sb.append('=');
        sb.append(((this.vesselName == null) ? "<null>" : this.vesselName));
        sb.append(',');
        sb.append("bmwBusinessUnit");
        sb.append('=');
        sb.append(((this.bmwBusinessUnit == null) ? "<null>" : this.bmwBusinessUnit));
        sb.append(',');
        sb.append("correlationId");
        sb.append('=');
        sb.append(((this.correlationId == null) ? "<null>" : this.correlationId));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}