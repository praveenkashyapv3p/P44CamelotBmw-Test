package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryInformation {
    
    @SerializedName("etaDateTimeUtc")
    @Expose
    private String etaDateTimeUtc;
    @SerializedName("etaDateRoutePartUtc")
    @Expose
    private String etaDateRoutePartUtc;
    @SerializedName("etdDateNextRoutePartUtc")
    @Expose
    private String etdDateNextRoutePart;
    @SerializedName("eventCreationDateTimeUtc")
    @Expose
    private String eventCreationDateTimeUtc;
    @SerializedName("eventSendingDateTimeUtc")
    @Expose
    private String eventSendingDateTimeUtc;
    @SerializedName("planDeliveryDate")
    @Expose
    private String planDeliveryDate;
    @SerializedName("planPickUpDate")
    @Expose
    private String planPickUpDate;
    
    public String getEtaDateTimeUtc() {
        return etaDateTimeUtc;
    }
    
    public void setEtaDateTimeUtc(String etaDateTimeUtc) {
        this.etaDateTimeUtc = etaDateTimeUtc;
    }
    
    public String getEtaDateRoutePartUtc() {
        return etaDateRoutePartUtc;
    }
    
    public void setEtaDateRoutePartUtc(String etaDateRoutePartUtc) {
        this.etaDateRoutePartUtc = etaDateRoutePartUtc;
    }
    
    public String getEtdDateNextRoutePart() {
        return etdDateNextRoutePart;
    }
    
    public void setEtdDateNextRoutePart(String etdDateNextRoutePart) {
        this.etdDateNextRoutePart = etdDateNextRoutePart;
    }
    
    public String getEventCreationDateTimeUtc() {
        return eventCreationDateTimeUtc;
    }
    
    public void setEventCreationDateTimeUtc(String eventCreationDateTimeUtc) {
        this.eventCreationDateTimeUtc = eventCreationDateTimeUtc;
    }
    
    public String getEventSendingDateTimeUtc() {
        return eventSendingDateTimeUtc;
    }
    
    public void setEventSendingDateTimeUtc(String eventSendingDateTimeUtc) {
        this.eventSendingDateTimeUtc = eventSendingDateTimeUtc;
    }
    
    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }
    
    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }
    
    public String getPlanPickUpDate() {
        return planPickUpDate;
    }
    
    public void setPlanPickUpDate(String planPickUpDate) {
        this.planPickUpDate = planPickUpDate;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DeliveryInformation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("etaDateTimeUtc");
        sb.append('=');
        sb.append(((this.etaDateTimeUtc == null) ? "<null>" : this.etaDateTimeUtc));
        sb.append(',');
        sb.append("etaDateRoutePartUtc");
        sb.append('=');
        sb.append(((this.etaDateRoutePartUtc == null) ? "<null>" : this.etaDateRoutePartUtc));
        sb.append(',');
        sb.append("etdDateNextRoutePart");
        sb.append('=');
        sb.append(((this.etdDateNextRoutePart == null) ? "<null>" : this.etdDateNextRoutePart));
        sb.append(',');
        sb.append("eventCreationDateTimeUtc");
        sb.append('=');
        sb.append(((this.eventCreationDateTimeUtc == null) ? "<null>" : this.eventCreationDateTimeUtc));
        sb.append(',');
        sb.append("eventSendingDateTimeUtc");
        sb.append('=');
        sb.append(((this.eventSendingDateTimeUtc == null) ? "<null>" : this.eventSendingDateTimeUtc));
        sb.append(',');
        sb.append("planDeliveryDate");
        sb.append('=');
        sb.append(((this.planDeliveryDate == null) ? "<null>" : this.planDeliveryDate));
        sb.append(',');
        sb.append("planPickUpDate");
        sb.append('=');
        sb.append(((this.planPickUpDate == null) ? "<null>" : this.planPickUpDate));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}