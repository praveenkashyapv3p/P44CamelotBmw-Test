package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sender {
    
    @SerializedName("senderID")
    @Expose
    private String senderID;
    @SerializedName("name")
    @Expose
    private String name;
    
    public String getSenderID() {
        return senderID;
    }
    
    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Sender.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("senderID");
        sb.append('=');
        sb.append(((this.senderID == null) ? "<null>" : this.senderID));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}