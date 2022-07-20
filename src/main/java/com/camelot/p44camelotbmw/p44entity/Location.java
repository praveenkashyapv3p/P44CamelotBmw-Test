package com.camelot.p44camelotbmw.p44entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {
    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_Location> identifiers = null;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Identifier_Location> getIdentifiers() {
        return identifiers;
    }
    
    public void setIdentifiers(List<Identifier_Location> identifiers) {
        this.identifiers = identifiers;
    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
}