package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BmwToP44 {
    
    @SerializedName("identifiers")
    @Expose
    private Identifiers identifiers;
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("contactInformation")
    @Expose
    private List<ContactInformation> contactInformation = null;
    @SerializedName("deliveryInformations")
    @Expose
    private DeliveryInformations deliveryInformations;
    @SerializedName("containerDimensions")
    @Expose
    private ContainerDimensions containerDimensions;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;
    
    public Identifiers getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }
    
    public String getTransportationNetwork() {
        return transportationNetwork;
    }
    
    public void setTransportationNetwork(String transportationNetwork) {
        this.transportationNetwork = transportationNetwork;
    }
    
    public List<ContactInformation> getContactInformation() {
        return contactInformation;
    }
    
    public void setContactInformation(List<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }
    
    public DeliveryInformations getDeliveryInformations() {
        return deliveryInformations;
    }
    
    public void setDeliveryInformations(DeliveryInformations deliveryInformations) {
        this.deliveryInformations = deliveryInformations;
    }
    
    public ContainerDimensions getContainerDimensions() {
        return containerDimensions;
    }
    
    public void setContainerDimensions(ContainerDimensions containerDimensions) {
        this.containerDimensions = containerDimensions;
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
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        sb.append("transportationNetwork");
        sb.append('=');
        sb.append(((this.transportationNetwork == null) ? "<null>" : this.transportationNetwork));
        sb.append(',');
        sb.append("contactInformation");
        sb.append('=');
        sb.append(((this.contactInformation == null) ? "<null>" : this.contactInformation));
        sb.append(',');
        sb.append("deliveryInformations");
        sb.append('=');
        sb.append(((this.deliveryInformations == null) ? "<null>" : this.deliveryInformations));
        sb.append(',');
        sb.append("containerDimensions");
        sb.append('=');
        sb.append(((this.containerDimensions == null) ? "<null>" : this.containerDimensions));
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