package com.camelot.p44camelotbmw.entity.fromP44Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class P44Shipment {
    
    @SerializedName("shipment")
    @Expose
    private Shipment shipment;
    @SerializedName("states")
    @Expose
    private List<State> states = null;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("positions")
    @Expose
    private List<Object> positions = null;
    
    public Shipment getShipment() {
        return shipment;
    }
    
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
    
    public List<State> getStates() {
        return states;
    }
    
    public void setStates(List<State> states) {
        this.states = states;
    }
    
    public List<Event> getEvents() {
        return events;
    }
    
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public List<Object> getPositions() {
        return positions;
    }
    
    public void setPositions(List<Object> positions) {
        this.positions = positions;
    }
    
}