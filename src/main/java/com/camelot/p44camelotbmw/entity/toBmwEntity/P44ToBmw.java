package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class P44ToBmw {
    
    @SerializedName("lifecycleStatus")
    @Expose
    private String lifecycleStatus;
    @SerializedName("lifecycleStatusVerbose")
    @Expose
    private String lifecycleStatusVerbose;
    @SerializedName("mainTransportMode")
    @Expose
    private String mainTransportMode;
    @SerializedName("transportationNetwork")
    @Expose
    private String transportationNetwork;
    @SerializedName("leadTime")
    @Expose
    private String leadTime;
    @SerializedName("currentLeadTimePerLeg")
    @Expose
    private String currentLeadTimePerLeg;
    @SerializedName("currentLeadTimePickUpUntilCurrentTimestamp")
    @Expose
    private String currentLeadTimePickUpUntilCurrentTimestamp;
    @SerializedName("currentLeadTimePickUpUntilDelivery")
    @Expose
    private String currentLeadTimePickUpUntilDelivery;
    @SerializedName("currentLeadTimePickUpUntilEta")
    @Expose
    private String currentLeadTimePickUpUntilEta;
    @SerializedName("recipient")
    @Expose
    private Recipient recipient;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("carrier")
    @Expose
    private Carrier carrier;
    @SerializedName("currentLocationInfo")
    @Expose
    private CurrentLocationInfo currentLocationInfo;
    @SerializedName("deliveryInformation")
    @Expose
    private DeliveryInformation deliveryInformation;
    @SerializedName("identifier")
    @Expose
    private Identifier identifier;
    @SerializedName("transportLegInfo")
    @Expose
    private TransportLegInfo transportLegInfo;
    @SerializedName("containerDimension")
    @Expose
    private ContainerDimension containerDimension;
    @SerializedName("materials")
    @Expose
    private List<Material> materials = null;
    
    public String getLifecycleStatus() {
        return lifecycleStatus;
    }
    
    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }
    
    public String getLifecycleStatusVerbose() {
        return lifecycleStatusVerbose;
    }
    
    public void setLifecycleStatusVerbose(String lifecycleStatusVerbose) {
        this.lifecycleStatusVerbose = lifecycleStatusVerbose;
    }
    
    public String getMainTransportMode() {
        return mainTransportMode;
    }
    
    public void setMainTransportMode(String mainTransportMode) {
        this.mainTransportMode = mainTransportMode;
    }
    
    public String getTransportationNetwork() {
        return transportationNetwork;
    }
    
    public void setTransportationNetwork(String transportationNetwork) {
        this.transportationNetwork = transportationNetwork;
    }
    
    public String getLeadTime() {
        return leadTime;
    }
    
    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }
    
    public String getCurrentLeadTimePerLeg() {
        return currentLeadTimePerLeg;
    }
    
    public void setCurrentLeadTimePerLeg(String currentLeadTimePerLeg) {
        this.currentLeadTimePerLeg = currentLeadTimePerLeg;
    }
    
    public String getCurrentLeadTimePickUpUntilCurrentTimestamp() {
        return currentLeadTimePickUpUntilCurrentTimestamp;
    }
    
    public void setCurrentLeadTimePickUpUntilCurrentTimestamp(String currentLeadTimePickUpUntilCurrentTimestamp) {
        this.currentLeadTimePickUpUntilCurrentTimestamp = currentLeadTimePickUpUntilCurrentTimestamp;
    }
    
    public String getCurrentLeadTimePickUpUntilDelivery() {
        return currentLeadTimePickUpUntilDelivery;
    }
    
    public void setCurrentLeadTimePickUpUntilDelivery(String currentLeadTimePickUpUntilDelivery) {
        this.currentLeadTimePickUpUntilDelivery = currentLeadTimePickUpUntilDelivery;
    }
    
    public String getCurrentLeadTimePickUpUntilEta() {
        return currentLeadTimePickUpUntilEta;
    }
    
    public void setCurrentLeadTimePickUpUntilEta(String currentLeadTimePickUpUntilEta) {
        this.currentLeadTimePickUpUntilEta = currentLeadTimePickUpUntilEta;
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
    
    public CurrentLocationInfo getCurrentLocationInfo() {
        return currentLocationInfo;
    }
    
    public void setCurrentLocationInfo(CurrentLocationInfo currentLocationInfo) {
        this.currentLocationInfo = currentLocationInfo;
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
    
    public TransportLegInfo getTransportLegInfo() {
        return transportLegInfo;
    }
    
    public void setTransportLegInfo(TransportLegInfo transportLegInfo) {
        this.transportLegInfo = transportLegInfo;
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
        sb.append(P44ToBmw.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lifecycleStatus");
        sb.append('=');
        sb.append(((this.lifecycleStatus == null) ? "<null>" : this.lifecycleStatus));
        sb.append(',');
        sb.append("lifecycleStatusVerbose");
        sb.append('=');
        sb.append(((this.lifecycleStatusVerbose == null) ? "<null>" : this.lifecycleStatusVerbose));
        sb.append(',');
        sb.append("mainTransportMode");
        sb.append('=');
        sb.append(((this.mainTransportMode == null) ? "<null>" : this.mainTransportMode));
        sb.append(',');
        sb.append("transportationNetwork");
        sb.append('=');
        sb.append(((this.transportationNetwork == null) ? "<null>" : this.transportationNetwork));
        sb.append(',');
        sb.append("leadTime");
        sb.append('=');
        sb.append(((this.leadTime == null) ? "<null>" : this.leadTime));
        sb.append(',');
        sb.append("currentLeadTimePerLeg");
        sb.append('=');
        sb.append(((this.currentLeadTimePerLeg == null) ? "<null>" : this.currentLeadTimePerLeg));
        sb.append(',');
        sb.append("currentLeadTimePickUpUntilCurrentTimestamp");
        sb.append('=');
        sb.append(((this.currentLeadTimePickUpUntilCurrentTimestamp == null) ? "<null>" : this.currentLeadTimePickUpUntilCurrentTimestamp));
        sb.append(',');
        sb.append("currentLeadTimePickUpUntilDelivery");
        sb.append('=');
        sb.append(((this.currentLeadTimePickUpUntilDelivery == null) ? "<null>" : this.currentLeadTimePickUpUntilDelivery));
        sb.append(',');
        sb.append("currentLeadTimePickUpUntilEta");
        sb.append('=');
        sb.append(((this.currentLeadTimePickUpUntilEta == null) ? "<null>" : this.currentLeadTimePickUpUntilEta));
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
        sb.append("currentLocationInfo");
        sb.append('=');
        sb.append(((this.currentLocationInfo == null) ? "<null>" : this.currentLocationInfo));
        sb.append(',');
        sb.append("deliveryInformation");
        sb.append('=');
        sb.append(((this.deliveryInformation == null) ? "<null>" : this.deliveryInformation));
        sb.append(',');
        sb.append("identifier");
        sb.append('=');
        sb.append(((this.identifier == null) ? "<null>" : this.identifier));
        sb.append(',');
        sb.append("transportLegInfo");
        sb.append('=');
        sb.append(((this.transportLegInfo == null) ? "<null>" : this.transportLegInfo));
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