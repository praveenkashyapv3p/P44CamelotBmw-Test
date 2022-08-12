package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipient {
    
    @SerializedName("recipientID")
    @Expose
    private String recipientID;
    @SerializedName("recipientName")
    @Expose
    private String recipientName;
    @SerializedName("recipientUnloadingPoint")
    @Expose
    private String recipientUnloadingPoint;
    
    public String getRecipientID() {
        return recipientID;
    }
    
    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }
    
    public String getRecipientName() {
        return recipientName;
    }
    
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    
    public String getRecipientUnloadingPoint() {
        return recipientUnloadingPoint;
    }
    
    public void setRecipientUnloadingPoint(String recipientUnloadingPoint) {
        this.recipientUnloadingPoint = recipientUnloadingPoint;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Recipient.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("recipientID");
        sb.append('=');
        sb.append(((this.recipientID == null) ? "<null>" : this.recipientID));
        sb.append(',');
        sb.append("recipientName");
        sb.append('=');
        sb.append(((this.recipientName == null) ? "<null>" : this.recipientName));
        sb.append(',');
        sb.append("recipientUnloadingPoint");
        sb.append('=');
        sb.append(((this.recipientUnloadingPoint == null) ? "<null>" : this.recipientUnloadingPoint));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}