
package com.camelot.p44camelotbmw.bmwentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactInformation {

    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("recepient")
    @Expose
    private Recepient recepient;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Recepient getRecepient() {
        return recepient;
    }

    public void setRecepient(Recepient recepient) {
        this.recepient = recepient;
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
        sb.append("recepient");
        sb.append('=');
        sb.append(((this.recepient == null) ? "<null>" : this.recepient));
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
