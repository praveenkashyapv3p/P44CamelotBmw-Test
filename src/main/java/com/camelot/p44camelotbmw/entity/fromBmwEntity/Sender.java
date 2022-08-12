package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender {
    
    @SerializedName("senderID")
    @Expose
    private String senderID;
    @SerializedName("senderName")
    @Expose
    private String senderName;
    
    public String getSenderID() {
        return senderID;
    }
    
    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
    
    public String getSenderName() {
        return senderName;
    }
    
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Sender.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("senderID");
        sb.append('=');
        sb.append(((this.senderID == null) ? "<null>" : this.senderID));
        sb.append(',');
        sb.append("senderName");
        sb.append('=');
        sb.append(((this.senderName == null) ? "<null>" : this.senderName));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}