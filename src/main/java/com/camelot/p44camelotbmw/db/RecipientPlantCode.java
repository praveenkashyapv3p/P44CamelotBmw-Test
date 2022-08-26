package com.camelot.p44camelotbmw.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipientPlantCode")
public class RecipientPlantCode {
    @Id
    public String id;
    
    String plantCode;
    String p44DisplayPlant;
    String plantDescription;
    String onwardCarriageToLocation;
    
    public RecipientPlantCode(String plantCode, String p44DisplayPlant, String plantDescription, String onwardCarriageToLocation) {
        this.plantCode = plantCode;
        this.p44DisplayPlant = p44DisplayPlant;
        this.plantDescription = plantDescription;
        this.onwardCarriageToLocation = onwardCarriageToLocation;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPlantCode() {
        return plantCode;
    }
    
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }
    
    public String getP44DisplayPlant() {
        return p44DisplayPlant;
    }
    
    public void setP44DisplayPlant(String p44DisplayPlant) {
        this.p44DisplayPlant = p44DisplayPlant;
    }
    
    public String getPlantDescription() {
        return plantDescription;
    }
    
    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }
    
    public String getOnwardCarriageToLocation() {
        return onwardCarriageToLocation;
    }
    
    public void setOnwardCarriageToLocation(String onwardCarriageToLocation) {
        this.onwardCarriageToLocation = onwardCarriageToLocation;
    }
    
    @Override
    public String toString() {
        return "RecipientPlantCode{" +
                "id='" + id + '\'' +
                ", plantCode='" + plantCode + '\'' +
                ", p44DisplayPlant='" + p44DisplayPlant + '\'' +
                ", plantDescription='" + plantDescription + '\'' +
                ", onwardCarriageToLocation='" + onwardCarriageToLocation + '\'' +
                '}';
    }
}