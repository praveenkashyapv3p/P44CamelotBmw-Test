package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identifiers {
    
    @SerializedName("containerID")
    @Expose
    private String containerID;
    @SerializedName("shipmentID")
    @Expose
    private String shipmentID;
    
    public String getContainerID() {
        return containerID;
    }
    
    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }
    
    public String getShipmentID() {
        return shipmentID;
    }
    
    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Identifiers.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("containerID");
        sb.append('=');
        sb.append(((this.containerID == null) ? "<null>" : this.containerID));
        sb.append(',');
        sb.append("shipmentID");
        sb.append('=');
        sb.append(((this.shipmentID == null) ? "<null>" : this.shipmentID));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}