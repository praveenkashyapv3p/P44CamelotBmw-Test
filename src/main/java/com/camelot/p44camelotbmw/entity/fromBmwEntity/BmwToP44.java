package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BmwToP44 {
    
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    @SerializedName("deliveryInformation")
    @Expose
    private DeliveryInformation deliveryInformation;
    @SerializedName("identifier")
    @Expose
    private Identifier identifier;
    @SerializedName("containerDimension")
    @Expose
    private ContainerDimension containerDimension;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;
    
    public String getTransportationNetwork() {
        return transportationNetwork;
    }
    
    public void setTransportationNetwork(String transportationNetwork) {
        this.transportationNetwork = transportationNetwork;
    }
    
    public Recipient getRecipient() {
        return recipient;
    }
    
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
    
    public Sender getSender() {
        return sender;
    }
    
    public void setSender(Sender sender) {
        this.sender = sender;
    }
    
    public Carrier getCarrier() {
        return carrier;
    }
    
    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
    
    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }
    
    public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }
    
    public Identifier getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
    
    public ContainerDimension getContainerDimension() {
        return containerDimension;
    }
    
    public void setContainerDimension(ContainerDimension containerDimension) {
        this.containerDimension = containerDimension;
    }
    
    public List<Material> getMaterials() {
        return materials;
    }
    
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BmwToP44.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("transportationNetwork");
        sb.append('=');
        sb.append(((this.transportationNetwork == null) ? "<null>" : this.transportationNetwork));
        sb.append(',');
        sb.append("recipient");
        sb.append('=');
        sb.append(((this.recipient == null) ? "<null>" : this.recipient));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null) ? "<null>" : this.sender));
        sb.append(',');
        sb.append("carrier");
        sb.append('=');
        sb.append(((this.carrier == null) ? "<null>" : this.carrier));
        sb.append(',');
        sb.append("deliveryInformation");
        sb.append('=');
        sb.append(((this.deliveryInformation == null) ? "<null>" : this.deliveryInformation));
        sb.append(',');
        sb.append("identifier");
        sb.append('=');
        sb.append(((this.identifier == null) ? "<null>" : this.identifier));
        sb.append(',');
        sb.append("containerDimension");
        sb.append('=');
        sb.append(((this.containerDimension == null) ? "<null>" : this.containerDimension));
        sb.append(',');
        sb.append("materials");
        sb.append('=');
        sb.append(((this.materials == null) ? "<null>" : this.materials));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}