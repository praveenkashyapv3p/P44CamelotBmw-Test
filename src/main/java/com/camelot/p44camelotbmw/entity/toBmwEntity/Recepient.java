package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Recepient {
    
    @SerializedName("recipientId")
    @Expose
    private String recipientID;
    @SerializedName("recipientName")
    @Expose
    private String name;
    
    public String getRecipientID() {
        return recipientID;
    }
    
    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
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
        sb.append(Recepient.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("recipientID");
        sb.append('=');
        sb.append(((this.recipientID == null) ? "<null>" : this.recipientID));
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