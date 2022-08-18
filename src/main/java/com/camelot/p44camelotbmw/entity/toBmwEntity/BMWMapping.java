package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BMWMapping {
    
    @SerializedName("identifiers")
    @Expose
    private Identifiers identifiers;
    @SerializedName("contactInformation")
    @Expose
    private List<ContactInformation> contactInformation = null;
    @SerializedName("currentLocationInfos")
    @Expose
    private CurrentLocationInfos currentLocationInfos;
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("mainTransportMode")
    @Expose
    private String mainTransportMode;
    @SerializedName("deliveryInformations")
    @Expose
    private DeliveryInformations deliveryInformations;
    @SerializedName("transportLegInfos")
    @Expose
    private List<TransportLegInfo> transportLegInfos = null;
    @SerializedName("containerDimensions")
    @Expose
    private ContainerDimensions containerDimensions;
    @SerializedName("materials")
    @Expose
    private List<JsonArray> materials = null;
    @SerializedName("technicalDetails")
    @Expose
    private List<TechnicalDetail> technicalDetails = null;
    
    public Identifiers getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }
    
    public List<ContactInformation> getContactInformation() {
        return contactInformation;
    }
    
    public void setContactInformation(List<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }
    
    public CurrentLocationInfos getCurrentLocationInfos() {
        return currentLocationInfos;
    }
    
    public void setCurrentLocationInfos(CurrentLocationInfos currentLocationInfos) {
        this.currentLocationInfos = currentLocationInfos;
    }
    
    public String getTransportationNetwork() {
        return transportationNetwork;
    }
    
    public void setTransportationNetwork(String transportationNetwork) {
        this.transportationNetwork = transportationNetwork;
    }
    
    public String getMainTransportMode() {
        return mainTransportMode;
    }
    
    public void setMainTransportMode(String mainTransportMode) {
        this.mainTransportMode = mainTransportMode;
    }
    
    public DeliveryInformations getDeliveryInformations() {
        return deliveryInformations;
    }
    
    public void setDeliveryInformations(DeliveryInformations deliveryInformations) {
        this.deliveryInformations = deliveryInformations;
    }
    
    public List<TransportLegInfo> getTransportLegInfos() {
        return transportLegInfos;
    }
    
    public void setTransportLegInfos(List<TransportLegInfo> transportLegInfos) {
        this.transportLegInfos = transportLegInfos;
    }
    
    public ContainerDimensions getContainerDimensions() {
        return containerDimensions;
    }
    
    public void setContainerDimensions(ContainerDimensions containerDimensions) {
        this.containerDimensions = containerDimensions;
    }
    
    public List<JsonArray> getMaterials() {
        return materials;
    }
    
    public void setMaterials(List<JsonArray> materials) {
        this.materials = materials;
    }
    
    public List<TechnicalDetail> getTechnicalDetails() {
        return technicalDetails;
    }
    
    public void setTechnicalDetails(List<TechnicalDetail> technicalDetails) {
        this.technicalDetails = technicalDetails;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BMWMapping.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("identifiers");
        sb.append('=');
        sb.append(((this.identifiers == null) ? "<null>" : this.identifiers));
        sb.append(',');
        sb.append("contactInformation");
        sb.append('=');
        sb.append(((this.contactInformation == null) ? "<null>" : this.contactInformation));
        sb.append(',');
        sb.append("currentLocationInfos");
        sb.append('=');
        sb.append(((this.currentLocationInfos == null) ? "<null>" : this.currentLocationInfos));
        sb.append(',');
        sb.append("transportationNetwork");
        sb.append('=');
        sb.append(((this.transportationNetwork == null) ? "<null>" : this.transportationNetwork));
        sb.append(',');
        sb.append("mainTransportMode");
        sb.append('=');
        sb.append(((this.mainTransportMode == null) ? "<null>" : this.mainTransportMode));
        sb.append(',');
        sb.append("deliveryInformations");
        sb.append('=');
        sb.append(((this.deliveryInformations == null) ? "<null>" : this.deliveryInformations));
        sb.append(',');
        sb.append("transportLegInfos");
        sb.append('=');
        sb.append(((this.transportLegInfos == null) ? "<null>" : this.transportLegInfos));
        sb.append(',');
        sb.append("containerDimensions");
        sb.append('=');
        sb.append(((this.containerDimensions == null) ? "<null>" : this.containerDimensions));
        sb.append(',');
        sb.append("materials");
        sb.append('=');
        sb.append(((this.materials == null) ? "<null>" : this.materials));
        sb.append(',');
        sb.append("technicalDetails");
        sb.append('=');
        sb.append(((this.technicalDetails == null) ? "<null>" : this.technicalDetails));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}