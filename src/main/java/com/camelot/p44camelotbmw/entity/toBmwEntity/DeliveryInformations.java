package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DeliveryInformations {
    
    @SerializedName("planPickUpDate")
    @Expose
    private String planPickUpDate;
    @SerializedName("planDeliveryDate")
    @Expose
    private String planDeliveryDate;
    @SerializedName("etaDateTimeUTC")
    @Expose
    private String etaDateTimeUTC;
    @SerializedName("etaDateRoutePartUTC")
    @Expose
    private String etaDateRoutePartUTC;
    @SerializedName("etdDateNextRoutePart")
    @Expose
    private String etdDateNextRoutePart;
    @SerializedName("eventCreationDateTimeUTC")
    @Expose
    private String eventCreationDateTimeUTC;
    @SerializedName("eventSendingDateTimeUTC")
    @Expose
    private String eventSendingDateTimeUTC;
    
    public String getPlanPickUpDate() {
        return planPickUpDate;
    }
    
    public void setPlanPickUpDate(String planPickUpDate) {
        this.planPickUpDate = planPickUpDate;
    }
    
    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }
    
    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }
    
    public String getEtaDateTimeUTC() {
        return etaDateTimeUTC;
    }
    
    public void setEtaDateTimeUTC(String etaDateTimeUTC) {
        this.etaDateTimeUTC = etaDateTimeUTC;
    }
    
    public String getEtaDateRoutePartUTC() {
        return etaDateRoutePartUTC;
    }
    
    public void setEtaDateRoutePartUTC(String etaDateRoutePartUTC) {
        this.etaDateRoutePartUTC = etaDateRoutePartUTC;
    }
    
    public String getEtdDateNextRoutePart() {
        return etdDateNextRoutePart;
    }
    
    public void setEtdDateNextRoutePart(String etdDateNextRoutePart) {
        this.etdDateNextRoutePart = etdDateNextRoutePart;
    }
    
    public String getEventCreationDateTimeUTC() {
        return eventCreationDateTimeUTC;
    }
    
    public void setEventCreationDateTimeUTC(String eventCreationDateTimeUTC) {
        this.eventCreationDateTimeUTC = eventCreationDateTimeUTC;
    }
    
    public String getEventSendingDateTimeUTC() {
        return eventSendingDateTimeUTC;
    }
    
    public void setEventSendingDateTimeUTC(String eventSendingDateTimeUTC) {
        this.eventSendingDateTimeUTC = eventSendingDateTimeUTC;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DeliveryInformations.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("planPickUpDate");
        sb.append('=');
        sb.append(((this.planPickUpDate == null) ? "<null>" : this.planPickUpDate));
        sb.append(',');
        sb.append("planDeliveryDate");
        sb.append('=');
        sb.append(((this.planDeliveryDate == null) ? "<null>" : this.planDeliveryDate));
        sb.append(',');
        sb.append("etaDateTimeUTC");
        sb.append('=');
        sb.append(((this.etaDateTimeUTC == null) ? "<null>" : this.etaDateTimeUTC));
        sb.append(',');
        sb.append("etaDateRoutePartUTC");
        sb.append('=');
        sb.append(((this.etaDateRoutePartUTC == null) ? "<null>" : this.etaDateRoutePartUTC));
        sb.append(',');
        sb.append("etdDateNextRoutePart");
        sb.append('=');
        sb.append(((this.etdDateNextRoutePart == null) ? "<null>" : this.etdDateNextRoutePart));
        sb.append(',');
        sb.append("eventCreationDateTimeUTC");
        sb.append('=');
        sb.append(((this.eventCreationDateTimeUTC == null) ? "<null>" : this.eventCreationDateTimeUTC));
        sb.append(',');
        sb.append("eventSendingDateTimeUTC");
        sb.append('=');
        sb.append(((this.eventSendingDateTimeUTC == null) ? "<null>" : this.eventSendingDateTimeUTC));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}