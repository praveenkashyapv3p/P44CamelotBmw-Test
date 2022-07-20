package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Carrier {
    
    @SerializedName("carrierID")
    @Expose
    private String carrierID;
    @SerializedName("carrierName")
    @Expose
    private String carrierName;
    
    public String getCarrierID() {
        return carrierID;
    }
    
    public void setCarrierID(String carrierID) {
        this.carrierID = carrierID;
    }
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Carrier.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("carrierID");
        sb.append('=');
        sb.append(((this.carrierID == null) ? "<null>" : this.carrierID));
        sb.append(',');
        sb.append("carrierName");
        sb.append('=');
        sb.append(((this.carrierName == null) ? "<null>" : this.carrierName));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}