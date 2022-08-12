package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ContactInformation {
    
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    
    public Sender getSender() {
        return sender;
    }
    
    public void setSender(Sender sender) {
        this.sender = sender;
    }
    
    public Recipient getRecipient() {
        return recipient;
    }
    
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
    
    public Carrier getCarrier() {
        return carrier;
    }
    
    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ContactInformation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null) ? "<null>" : this.sender));
        sb.append(',');
        sb.append("recipient");
        sb.append('=');
        sb.append(((this.recipient == null) ? "<null>" : this.recipient));
        sb.append(',');
        sb.append("carrier");
        sb.append('=');
        sb.append(((this.carrier == null) ? "<null>" : this.carrier));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}