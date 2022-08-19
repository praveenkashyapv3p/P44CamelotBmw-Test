package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportLegInfo {
    
    @SerializedName("currentTransportMode")
    @Expose
    private String currentTransportMode;
    @SerializedName("transportSection")
    @Expose
    private String transportSection;
    @SerializedName("pointOfLoading")
    @Expose
    private PointOfLoading pointOfLoading;
    @SerializedName("leg1")
    @Expose
    private TransportLeg1 transportLeg1 = null;
    @SerializedName("leg2")
    @Expose
    private TransportLeg2 transportLeg2 = null;
    @SerializedName("leg3")
    @Expose
    private TransportLeg3 transportLeg3 = null;
    @SerializedName("leg4")
    @Expose
    private TransportLeg4 transportLeg4 = null;
    @SerializedName("leg5")
    @Expose
    private TransportLeg5 transportLeg5 = null;
    @SerializedName("leg6")
    @Expose
    private TransportLeg6 transportLeg6 = null;
    @SerializedName("leg7")
    @Expose
    private TransportLeg7 transportLeg7 = null;
    @SerializedName("leg8")
    @Expose
    private TransportLeg8 transportLeg8 = null;
    @SerializedName("leg9")
    @Expose
    private TransportLeg9 transportLeg9 = null;
    @SerializedName("leg10")
    @Expose
    private TransportLeg10 transportLeg10 = null;
    @SerializedName("pointOfDelivery")
    @Expose
    private PointOfDelivery pointOfDelivery;
    
    public String getCurrentTransportMode() {
        return currentTransportMode;
    }
    
    public void setCurrentTransportMode(String currentTransportMode) {
        this.currentTransportMode = currentTransportMode;
    }
    
    public String getTransportSection() {
        return transportSection;
    }
    
    public void setTransportSection(String transportSection) {
        this.transportSection = transportSection;
    }
    
    public PointOfLoading getPointOfLoading() {
        return pointOfLoading;
    }
    
    public void setPointOfLoading(PointOfLoading pointOfLoading) {
        this.pointOfLoading = pointOfLoading;
    }
    
    public TransportLeg1 getTransportLeg1() {
        return transportLeg1;
    }
    
    public void setTransportLeg1(TransportLeg1 transportLeg1) {
        this.transportLeg1 = transportLeg1;
    }
    
    public TransportLeg2 getTransportLeg2() {
        return transportLeg2;
    }
    
    public void setTransportLeg2(TransportLeg2 transportLeg2) {
        this.transportLeg2 = transportLeg2;
    }
    
    public TransportLeg3 getTransportLeg3() {
        return transportLeg3;
    }
    
    public void setTransportLeg3(TransportLeg3 transportLeg3) {
        this.transportLeg3 = transportLeg3;
    }
    
    public TransportLeg4 getTransportLeg4() {
        return transportLeg4;
    }
    
    public void setTransportLeg4(TransportLeg4 transportLeg4) {
        this.transportLeg4 = transportLeg4;
    }
    
    public TransportLeg5 getTransportLeg5() {
        return transportLeg5;
    }
    
    public void setTransportLeg5(TransportLeg5 transportLeg5) {
        this.transportLeg5 = transportLeg5;
    }
    
    public TransportLeg6 getTransportLeg6() {
        return transportLeg6;
    }
    
    public void setTransportLeg6(TransportLeg6 transportLeg6) {
        this.transportLeg6 = transportLeg6;
    }
    
    public TransportLeg7 getTransportLeg7() {
        return transportLeg7;
    }
    
    public void setTransportLeg7(TransportLeg7 transportLeg7) {
        this.transportLeg7 = transportLeg7;
    }
    
    public TransportLeg8 getTransportLeg8() {
        return transportLeg8;
    }
    
    public void setTransportLeg8(TransportLeg8 transportLeg8) {
        this.transportLeg8 = transportLeg8;
    }
    
    public TransportLeg9 getTransportLeg9() {
        return transportLeg9;
    }
    
    public void setTransportLeg9(TransportLeg9 transportLeg9) {
        this.transportLeg9 = transportLeg9;
    }
    
    public TransportLeg10 getTransportLeg10() {
        return transportLeg10;
    }
    
    public void setTransportLeg10(TransportLeg10 transportLeg10) {
        this.transportLeg10 = transportLeg10;
    }
    
    public PointOfDelivery getPointOfDelivery() {
        return pointOfDelivery;
    }
    
    public void setPointOfDelivery(PointOfDelivery pointOfDelivery) {
        this.pointOfDelivery = pointOfDelivery;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TransportLegInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentTransportMode");
        sb.append('=');
        sb.append(((this.currentTransportMode == null) ? "<null>" : this.currentTransportMode));
        sb.append(',');
        sb.append("transportSection");
        sb.append('=');
        sb.append(((this.transportSection == null) ? "<null>" : this.transportSection));
        sb.append(',');
        sb.append("pointOfLoading");
        sb.append('=');
        sb.append(((this.pointOfLoading == null) ? "<null>" : this.pointOfLoading));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg1 == null) ? "<null>" : this.transportLeg1));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg2 == null) ? "<null>" : this.transportLeg2));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg3 == null) ? "<null>" : this.transportLeg3));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg4 == null) ? "<null>" : this.transportLeg4));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg5 == null) ? "<null>" : this.transportLeg5));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg6 == null) ? "<null>" : this.transportLeg6));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg7 == null) ? "<null>" : this.transportLeg7));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg8 == null) ? "<null>" : this.transportLeg8));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg9 == null) ? "<null>" : this.transportLeg9));
        sb.append(',');
        sb.append("transportLeg");
        sb.append('=');
        sb.append(((this.transportLeg10 == null) ? "<null>" : this.transportLeg10));
        sb.append(',');
        sb.append("pointOfDelivery");
        sb.append('=');
        sb.append(((this.pointOfDelivery == null) ? "<null>" : this.pointOfDelivery));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}